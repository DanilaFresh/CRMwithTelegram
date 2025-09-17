package com.example.CRMforDelivery.exceptions;

import java.util.NoSuchElementException;

public class NoSuchCustomerException extends NoSuchElementException {
    public NoSuchCustomerException(String message) {
        super(message);
    }
}
