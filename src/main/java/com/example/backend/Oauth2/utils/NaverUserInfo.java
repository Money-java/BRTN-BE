package com.example.backend.Oauth2.utils;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;


@Builder
@Getter
public class NaverUserInfo{
    private String email;
    private String nickname;
    private String provider;

    public NaverUserInfo(String email, String nickname, String provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }
}
