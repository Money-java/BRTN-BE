package com.example.backend.Oauth2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
@PropertySource("classpath:application.properties")
public class GoogleOauth2Service implements CustomOauth2Service {


    @Value("${google.token-url}")
    private String GOOGLE_TOKEN_URL;
    @Value("${google.client-id}")
    private String GOOGLE_CLIENT_ID;
    @Value("${google.client-secret}")
    private String GOOGLE_CLIENT_SECRET;
    @Value("${google.redirect-url}")
    private String GOOGLE_REDIRECT_URL;

    //google code로 accessToken 받아오기
    @Override
    public ResponseEntity<String> getUserInfo(String accessCode) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();

        params.put("code", accessCode);
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("client_secret", GOOGLE_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_REDIRECT_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return null;
    }

}
