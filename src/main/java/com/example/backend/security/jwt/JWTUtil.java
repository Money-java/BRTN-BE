package com.example.backend.security.jwt;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {

    static private final int TOKEN_EXPIRATION = 60 * 24 * 60 * 1000;  // 밀리초로 설정 (24시간)

    private String secretKey = "MoneyJavaMoneyJavaMoneyJavaMoneyJava";

    private Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    // JWT 토큰 생성
    public String generateToken(String email, String provider, String role) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + TOKEN_EXPIRATION);

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        return Jwts.builder()
                .setHeader(headerMap)
                .setSubject(email)
                .claim("role", role)
                .claim("provider", provider)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(key)
                .compact();
    }

    // JWT 토큰에서 이메일 추출
    public String getUserEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // JWT 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        try {
            Date expirationDate = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();

            return expirationDate.before(new Date());  // 현재 시간과 만료 시간을 비교
        } catch (ExpiredJwtException e) {
            // 토큰이 이미 만료된 경우 예외가 발생함
            return true;
        }
    }
}
