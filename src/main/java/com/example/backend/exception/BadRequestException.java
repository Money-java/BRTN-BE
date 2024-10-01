package com.example.backend.exception;

// 400 Bad request
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super("The checkDate format is invalid.");
    }

    public BadRequestException(String message) {
        super(message);
    }
}
