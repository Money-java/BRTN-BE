package com.example.backend.Oauth2.controller;


import com.example.backend.Oauth2.service.GoogleOauth2Service;
import com.example.backend.Oauth2.service.KakaoOauth2Service;
import com.example.backend.Oauth2.service.NaverOauth2Service;
import com.example.backend.Oauth2.utils.GoogleUserInfo;
import com.example.backend.Oauth2.utils.KakaoUserInfo;
import com.example.backend.Oauth2.utils.NaverUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
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

    @Autowired
    private NaverOauth2Service naverOauth2Service;


    @GetMapping("/google")
    public GoogleUserInfo successGoogleLogin(
            @RequestParam("code") String code
    ) throws JsonProcessingException {
        ResponseEntity<String> response = googleOauth2Service.getUserProfile(code);
        return googleOauth2Service.getGoogleUserInfo(response);
    }

//    @GetMapping("/google")
//    public ResponseEntity<String> successGoogleLogin(
//            @RequestParam("code") String code
//    ) throws JsonProcessingException {
//        ResponseEntity<String> response = googleOauth2Service.getUserProfile(code);
//        return response;
//    }

    @GetMapping("/kakao")
    public KakaoUserInfo successKakaoLogin(
            @RequestParam("code") String accessCode
    ) throws JsonProcessingException {
        ResponseEntity<String> response = kakaoOauth2Service.getUserProfile(accessCode);
        return kakaoOauth2Service.getKakaoUserInfo(response);
    }


    @GetMapping("/naver")
    public NaverUserInfo successNaverLogin(
            @RequestParam("code") String accessCode
    ) throws JsonProcessingException {
        ResponseEntity<String> response = naverOauth2Service.getUserProfile(accessCode);
        return naverOauth2Service.getNaverUserInfo(response);
    }

}
