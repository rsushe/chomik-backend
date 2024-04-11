package com.chomik.eventservice.exception;

public class UnsupportedEventTypeException extends RuntimeException {
    public UnsupportedEventTypeException(String message) {
        super(message);
    }
}
