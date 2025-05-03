package com.edu.bits.cloud.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.bits.cloud.bank.entity.Account;
import com.edu.bits.cloud.bank.entity.Customer;
import com.edu.bits.cloud.bank.exception.CustomerNotFoundException;
import com.edu.bits.cloud.bank.mapper.CustomerMapper;
import com.edu.bits.cloud.bank.model.AccountModel;
import com.edu.bits.cloud.bank.model.CustomerModel;
import com.edu.bits.cloud.bank.repository.AccountRepository;
import com.edu.bits.cloud.bank.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private BusinessRulesService businessRulesService;

	/*
	 * @Autowired private CacheManager cacheManager;
	 * 
	 * private ObjectMapper objectMapper = new ObjectMapper();
	 */
	/**
	 * Retrieves the customer and account information for the given customerID.
	 * Applies caching and invokes AWS Lambda for business rule evaluation.
	 *
	 * @param customerID the ID to search for.
	 * @return a Map containing customer data, accounts list, and Lambda output.
	 */

	public CustomerModel getCustomerInfo(Integer customerID) {
		/*
		 * // Build a custom cache key. String cacheKey = "CUSTOMERID_" + customerID;
		 * 
		 * // Get the cache named "customerCache" from CacheManager. Cache cache =
		 * cacheManager.getCache("customerCache");
		 * 
		 * // Attempt to retrieve cached data. Map<String, Object> cachedData = (cache
		 * != null) ? cache.get(cacheKey, Map.class) : null;
		 * 
		 * // If data is found in cache, return it immediately. if (cachedData != null)
		 * { return cachedData; }
		 */
		// Data not in cache. Now query the database.
		Optional<Customer> optionalCustomer = customerRepository.findById(customerID);
		if (optionalCustomer.isEmpty()) {
			throw new CustomerNotFoundException(customerID);
		}
		Customer customer = optionalCustomer.get();

		// Retrieve associated accounts.
		List<Account> accounts = accountRepository.findByCustomer(customer);

		CustomerModel model = CustomerMapper.toModel(customer);
		for (Account acct : accounts) {
			AccountModel acctModel = CustomerMapper.toModel(acct);
			model.getAccounts().add(acctModel);
		}

		// Put the response into the cache for subsequent calls.
		/*
		 * if (cache != null) { cache.put(cacheKey, response); }
		 * 
		 * try { // Convert the response map to a JSON string String json =
		 * objectMapper.writeValueAsString(response); System.out.
		 * println("CustomerService.getCustomerInfo :: before running business rules");
		 * // Invoke AWS Lambda to evaluate business rules String lambdaResponse =
		 * businessRulesService.applyRules(json); System.out.
		 * println("CustomerService.getCustomerInfo :: After Successfully running business rules"
		 * ); response.put("businessRulesOutcome", lambdaResponse); } catch (Exception
		 * e) { response.put("businessRulesOutcome", "Error applying business rules: " +
		 * e.getMessage()); }
		 */
		return model;

	}
}
