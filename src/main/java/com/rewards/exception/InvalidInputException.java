package com.rewards.exception;

import lombok.Getter;

/**
 * Exception thrown when input parameters are invalid.
 */
@Getter
public class InvalidInputException extends RuntimeException {
    private final String errorMessage;

    public InvalidInputException(String message) {
        super(message);
        this.errorMessage = message;
    }
} 