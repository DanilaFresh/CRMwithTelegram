package com.example.CRMforDelivery.exceptions;

import java.util.NoSuchElementException;

public class NoSuchOrderException extends NoSuchElementException {
    public NoSuchOrderException(String message) {
        super(message);
    }
}
