package com.example.CRMforDelivery.exceptions;

import java.util.NoSuchElementException;

    public class NoSuchCourierException extends NoSuchElementException {
        public NoSuchCourierException(String message) {
            super(message);
        }
    }
