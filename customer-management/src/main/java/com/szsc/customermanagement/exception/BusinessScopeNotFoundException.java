package com.szsc.customermanagement.exception;

public class BusinessScopeNotFoundException extends Exception {

    public BusinessScopeNotFoundException(String message) {
        super(message);
    }

    public BusinessScopeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}