package com.szsc.customermanagement.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    @ResponseBody
    public ResponseEntity<?> handleCompanyAlreadyExistsException(CompanyAlreadyExistsException ex) {
        Map<String, Boolean> errorResponse = new HashMap<>();
        errorResponse.put("company_confilct", true);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


}
