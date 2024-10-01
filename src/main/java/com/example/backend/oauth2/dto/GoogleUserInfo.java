package com.example.backend.oauth2.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GoogleUserInfo {

    private String email;
    private String nickname;
    private String provider;

    public GoogleUserInfo(String email, String nickname, String provider) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
    }
}
