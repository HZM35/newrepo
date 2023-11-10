package com.hazemdev.contentcalender.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ContentNotFoundException extends Exception {

    HttpStatus responseStatus;
    String message;

    public ContentNotFoundException(HttpStatus responseStatus, String message) {
        this.responseStatus = responseStatus;
        this.message = message;
    }
}
