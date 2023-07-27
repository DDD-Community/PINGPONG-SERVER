package com.pingpong.quoteBakery.com.exception;

public class BusinessInvalidValueException extends RuntimeException {

    public BusinessInvalidValueException(String message) {
        super(message);
    }
}