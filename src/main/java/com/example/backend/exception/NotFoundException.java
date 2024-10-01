package com.example.backend.exception;

// 404 Not Found
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("The requested data could not be found.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
