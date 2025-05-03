package com.edu.bits.cloud.bank.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.edu.bits.cloud.bank.entity.Account;
import com.edu.bits.cloud.bank.entity.Customer;
import com.edu.bits.cloud.bank.model.AccountModel;
import com.edu.bits.cloud.bank.model.CustomerModel;

public class CustomerMapper {

	// Convert Customer entity to CustomerModel
	public static CustomerModel toModel(Customer customer) {
		if (customer == null) {
			return null;
		}
		CustomerModel model = new CustomerModel();
		model.setCustomerID(customer.getCustomerID());
		model.setName(customer.getName());
		model.setAge(customer.getAge());		
		model.setCountry(customer.getCountry());
		model.setState(customer.getState());
		model.setDistrict(customer.getDistrict());
		model.setPincode(customer.getPincode());
		model.setAddress(customer.getAddress());
		model.setGender(customer.getGender());

		// Map the associated accounts if any
		if (customer.getAccounts() != null) {
			List<AccountModel> accountModels = customer.getAccounts().stream().map(CustomerMapper::toModel)
					.collect(Collectors.toList());
			model.setAccounts(accountModels);
		}
		return model;
	}

	// Overloaded method to convert Account entity to AccountModel
	public static AccountModel toModel(Account account) {
		if (account == null) {
			return null;
		}
		AccountModel model = new AccountModel();
		model.setAccountID(account.getAccountID());
		model.setAccountNumber(account.getAccountNumber());
		model.setAccountType(account.getAccountType());
		model.setAccountBalance(account.getAccountBalance());
		return model;
	}
}
