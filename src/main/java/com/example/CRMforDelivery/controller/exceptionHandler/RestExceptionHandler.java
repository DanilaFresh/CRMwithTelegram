package com.example.CRMforDelivery.controller.exceptionHandler;


import com.example.CRMforDelivery.exceptions.NoSuchCourierException;
import com.example.CRMforDelivery.exceptions.NoSuchCustomerException;
import com.example.CRMforDelivery.exceptions.NoSuchOrderException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NoSuchCourierException.class)
    public ResponseEntity<String> handleNoSuchCourierException(NoSuchCourierException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(NoSuchCustomerException.class)
    public ResponseEntity<String> handleNoSuchCustomerException(NoSuchCustomerException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler(NoSuchOrderException.class)
    public ResponseEntity<String> handleNoSuchOrderException(NoSuchOrderException e) {
        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }
}
