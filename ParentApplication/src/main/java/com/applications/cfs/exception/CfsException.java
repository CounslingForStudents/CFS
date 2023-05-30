package com.applications.cfs.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CfsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public CfsException(String message) {
        super("ExtAPI: " + message);
    }

    public CfsException(String message, Throwable t) {
        super("ExtAPI: " + message, t);
    }
}

