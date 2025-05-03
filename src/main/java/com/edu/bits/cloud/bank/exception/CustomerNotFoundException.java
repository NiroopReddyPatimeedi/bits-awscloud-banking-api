package com.edu.bits.cloud.bank.exception;

public class CustomerNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Integer id) {
        super("Customer with ID " + id + " not found.");
    }
}

