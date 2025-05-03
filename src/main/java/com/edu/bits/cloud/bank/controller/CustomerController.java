package com.edu.bits.cloud.bank.controller;

import com.edu.bits.cloud.bank.model.CustomerModel;
import com.edu.bits.cloud.bank.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * GET endpoint to retrieve customer information by customerID.
     *
     * @param customerID the customer ID provided in the path.
     * @return JSON with customer data, accounts, and Lambda business rules output.
     */
    @GetMapping("/{customerID}")
    public ResponseEntity<?> getCustomerById(@PathVariable("customerID") Integer customerID) {
    	System.out.println("Inside CustomerController.getCustomerById :: customerID : "+customerID);
        CustomerModel result = customerService.getCustomerInfo(customerID);
        
        String json = "";
        try {
			json = objectMapper.writeValueAsString(result);
			System.out.println("Inside CustomerController.getCustomerById :: result : "+json);
		} catch (JsonProcessingException e) {
			json = "Exception occured, Please try again later";
			e.printStackTrace();
		}
        return result==null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
    }
}
