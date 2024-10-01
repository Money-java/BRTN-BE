package com.example.backend.exception;

// 401 Unauthorized
public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super("401 Unauthorized : 액세스 토큰이 유효하지 않거나 만료되었습니다.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
