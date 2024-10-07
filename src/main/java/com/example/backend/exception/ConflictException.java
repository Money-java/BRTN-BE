package com.example.backend.exception;

// 409 Conflict
public class ConflictException extends RuntimeException {
    public ConflictException() {
        super("The request could not be processed due to a conflict with the current state of the target resource.");
    }

    public ConflictException(String message) {
        super(message);
    }
}
