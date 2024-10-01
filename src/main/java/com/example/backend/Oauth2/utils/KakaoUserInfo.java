package com.example.backend.Oauth2.utils;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoUserInfo {

    private String email;
    private String nickname;
    private String provider;

    public KakaoUserInfo(String email, String nickname, String provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }
}
