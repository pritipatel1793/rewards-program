package com.rewards.exception;

import lombok.Getter;

/**
 * Exception thrown when a customer is not found in the database.
 */
@Getter
public class CustomerNotFoundException extends RuntimeException {
    private final Long customerId;

    public CustomerNotFoundException(Long customerId) {
        super("Customer not found with ID: " + customerId);
        this.customerId = customerId;
    }
} 