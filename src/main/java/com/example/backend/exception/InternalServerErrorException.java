package com.example.backend.exception;

// 500 Internal Server Error
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("An error occurred on the server.");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
