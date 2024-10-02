package com.example.backend.oauth2.controller;


import com.example.backend.Users.service.UserService;
import com.example.backend.Users.vo.UserVO;
import com.example.backend.oauth2.service.GoogleOauth2Service;
import com.example.backend.oauth2.service.KakaoOauth2Service;
import com.example.backend.oauth2.service.NaverOauth2Service;
import com.example.backend.oauth2.dto.GoogleUserInfo;
import com.example.backend.oauth2.dto.KakaoUserInfo;
import com.example.backend.oauth2.dto.NaverUserInfo;
import com.example.backend.security.util.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/login/oauth2/code")
public class OauthController {

    @Autowired
    private GoogleOauth2Service googleOauth2Service;

    @Autowired
    private KakaoOauth2Service kakaoOauth2Service;

    @Autowired
    private NaverOauth2Service naverOauth2Service;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;


    @GetMapping("/google")
    public GoogleUserInfo successGoogleLogin(
            @RequestParam("code") String code
    ) throws JsonProcessingException {
        ResponseEntity<String> response = googleOauth2Service.getUserProfile(code);
        return googleOauth2Service.getGoogleUserInfo(response);
    }

    @GetMapping("/kakao")
    public void successKakaoLogin(
            @RequestParam("code") String accessCode,
            HttpServletResponse response
    ) throws IOException {
        ResponseEntity<String> kakaoResponse = kakaoOauth2Service.getUserProfile(accessCode);

        KakaoUserInfo userInfo = kakaoOauth2Service.getKakaoUserInfo(kakaoResponse);

        UserVO existingUser = userService.findUserByEmail(userInfo.getEmail());

        if (existingUser == null) {
            // 사용자가 존재하지 않으면 DB에 저장
            UserVO newUser = new UserVO();
            newUser.setEmail(userInfo.getEmail());
            newUser.setName(userInfo.getNickname());
            newUser.setNickname("maxgun98");
            newUser.setProvider(userInfo.getProvider());
            newUser.setRole("ROLE_USER");  // 기본 역할 설정
            userService.registerUser(newUser);
        }
        String token = jwtUtil.generateToken(userInfo.getEmail(), userInfo.getProvider(), "ROLE_USER");
        // JWT 토큰을 쿠키에 담아서 설정
        Cookie jwtCookie = new Cookie("jwtToken", token);
        jwtCookie.setHttpOnly(true);  // 자바스크립트에서 접근하지 못하도록 설정
//        jwtCookie.setSecure(true);    // HTTPS에서만 사용
        jwtCookie.setPath("/");       // 애플리케이션 전체 경로에서 사용 가능
        jwtCookie.setMaxAge(60 * 60 * 24); // 1일 동안 유효

        // 쿠키를 응답에 추가
        response.addCookie(jwtCookie);

        // 프론트엔드로 리다이렉트
        response.sendRedirect("http://localhost:5173/");
    }


    @GetMapping("/naver")
    public NaverUserInfo successNaverLogin(
            @RequestParam("code") String accessCode
    ) throws JsonProcessingException {
        ResponseEntity<String> response = naverOauth2Service.getUserProfile(accessCode);
        return naverOauth2Service.getNaverUserInfo(response);
    }

}
