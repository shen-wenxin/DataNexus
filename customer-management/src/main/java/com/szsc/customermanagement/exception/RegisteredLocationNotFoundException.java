package com.szsc.customermanagement.exception;

public class RegisteredLocationNotFoundException extends RuntimeException {
    public RegisteredLocationNotFoundException(String message) {
        super(message);
    }
}
