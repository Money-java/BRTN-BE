package com.example.backend.oauth2.dto;

import lombok.Builder;
import lombok.Getter;


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
