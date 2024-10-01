package com.example.backend.exception;

// 500 Internal Server Error
public class InternalServerErrorException extends RuntimeException {
    public InternalServerErrorException() {
        super("500 Internal Server Error : 서버에서 오류가 발생했습니다.");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }
}
