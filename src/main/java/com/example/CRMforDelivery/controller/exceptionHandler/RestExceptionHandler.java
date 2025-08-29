package com.example.CRMforDelivery.controller.exceptionHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleNotValid(MethodArgumentNotValidException ex) throws JsonProcessingException {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField()+" "+x.getDefaultMessage())
                .toList();
        ApiErrorDescr apiErrorDescr=new ApiErrorDescr(
                "Not valid data",
                errors);
        return ResponseEntity
                .badRequest()
                .body(apiErrorDescr);
    }
}
