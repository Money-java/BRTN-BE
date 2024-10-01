package com.example.backend.Oauth2.controller;


import com.example.backend.Oauth2.service.GoogleOauth2Service;
import com.example.backend.Oauth2.service.KakaoOauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/oauth2/code")
public class OauthController {

    @Autowired
    private GoogleOauth2Service googleOauth2Service;

    @Autowired
    private KakaoOauth2Service kakaoOauth2Service;


    @GetMapping("/google")
    public ResponseEntity<String> successGoogleLogin(
            @RequestParam("code") String accessCode
    ){
        return googleOauth2Service.getUserInfo(accessCode);
    }

    @GetMapping("/kakao")
    public ResponseEntity<String> successKakaoLogin(
            @RequestParam("code") String accessCode
    ){
        String accessToken = kakaoOauth2Service.getAccessToken(accessCode);
        return kakaoOauth2Service.getUserInfo(accessToken);
    }

}
