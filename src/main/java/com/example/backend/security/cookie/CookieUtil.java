package com.example.backend.security.cookie;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;


@Component
public class CookieUtil {
    public Cookie generateCookie(String token){
        Cookie jwtCookie = new Cookie("jwtToken", token);
        jwtCookie.setHttpOnly(false);// 자바스크립트에서 접근하지 못하도록 설정
        jwtCookie.setSecure(false);
        jwtCookie.setPath("/");       // 애플리케이션 전체 경로에서 사용 가능
        jwtCookie.setMaxAge(60 * 60 * 24);// 1일 동안 유효
        return jwtCookie;
    }
}
