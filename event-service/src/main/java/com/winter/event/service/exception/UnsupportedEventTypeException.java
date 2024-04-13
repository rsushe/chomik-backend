package com.winter.event.service.exception;

public class UnsupportedEventTypeException extends RuntimeException {
    public UnsupportedEventTypeException(String message) {
        super(message);
    }
}
