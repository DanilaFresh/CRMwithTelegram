package com.example.CRMforDelivery.controller.exceptionHandler;

import java.util.List;

public class ApiErrorDescr {
    private String message;
    private List<String> detailMessage;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(List<String> detailMessage) {
        this.detailMessage = detailMessage;
    }

    public ApiErrorDescr() {
    }

    public ApiErrorDescr(String message, List<String> detailMessage) {
        this.message = message;
        this.detailMessage = detailMessage;
    }
}
