package com.example.backend.exception;

// 401 Unauthorized
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("The access token is invalid or has expired.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
