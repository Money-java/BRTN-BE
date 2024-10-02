package com.example.backend.oauth2.service;

import org.springframework.http.ResponseEntity;

public interface CustomOauth2Service {

    ResponseEntity<String> getUserProfile(String code);
    String getAccessToken(String code);
}
