package com.senyasas.cs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class myException extends ResponseStatusException {

    public myException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public myException(String message, Exception e) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, e);
    }
}
