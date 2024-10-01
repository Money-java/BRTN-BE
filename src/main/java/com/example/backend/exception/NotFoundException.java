package com.example.backend.exception;

// 404 Not Found
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("404 Not Found : 해당 데이터를 찾을 수 없습니다.");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
