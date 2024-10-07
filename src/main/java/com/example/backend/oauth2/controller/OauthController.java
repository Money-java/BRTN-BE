package com.example.backend.oauth2.controller;

import com.example.backend.Users.service.UsersService;
import com.example.backend.Users.vo.UserVO;
import com.example.backend.oauth2.service.GoogleOauth2Service;
import com.example.backend.oauth2.service.KakaoOauth2Service;
import com.example.backend.oauth2.service.NaverOauth2Service;
import com.example.backend.oauth2.dto.GoogleUserInfo;
import com.example.backend.oauth2.dto.KakaoUserInfo;
import com.example.backend.oauth2.dto.NaverUserInfo;
import com.example.backend.security.cookie.CookieUtil;
import com.example.backend.security.jwt.JWTUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
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
    private CookieUtil cookieUtil;

    @Autowired
    private UsersService userService;


    @GetMapping("/google")
    public void successGoogleLogin(
            @RequestParam("code") String code,
            HttpServletResponse response
    ) throws IOException {
        ResponseEntity<String> googleResponse = googleOauth2Service.getUserProfile(code);

        GoogleUserInfo userInfo = googleOauth2Service.getGoogleUserInfo(googleResponse);

        String token = jwtUtil.generateToken(userInfo.getEmail(), userInfo.getProvider(), "ROLE_USER");
        // JWT 토큰을 쿠키에 담아서 설정
        Cookie jwtCookie = cookieUtil.generateCookie(token);
        // 쿠키를 응답에 추가
        response.addCookie(jwtCookie);

        UserVO existingUser = userService.findUserByEmailandProvider(userInfo.getEmail(), userInfo.getProvider());

        if (existingUser == null) {
            // 사용자가 존재하지 않으면 DB에 저장
            UserVO newUser = new UserVO();
            newUser.setEmail(userInfo.getEmail());
            newUser.setName(userInfo.getNickname());
            newUser.setProvider(userInfo.getProvider());
            newUser.setRole("ROLE_USER");  // 기본 역할 설정
            userService.insertUser(newUser);

            response.sendRedirect("http://localhost:5173/register");
        }
        else {
            // 프론트엔드로 리다이렉트
            response.sendRedirect("http://localhost:5173/myroutine");
        }
    }

    @GetMapping("/kakao")
    public void successKakaoLogin(
            @RequestParam("code") String accessCode,
            HttpServletResponse response
    ) throws IOException {
        ResponseEntity<String> kakaoResponse = kakaoOauth2Service.getUserProfile(accessCode);

        KakaoUserInfo userInfo = kakaoOauth2Service.getKakaoUserInfo(kakaoResponse);

        String token = jwtUtil.generateToken(userInfo.getEmail(), userInfo.getProvider(), "ROLE_USER");
        // JWT 토큰을 쿠키에 담아서 설정
        Cookie jwtCookie = cookieUtil.generateCookie(token);
        // 쿠키를 응답에 추가
        response.addCookie(jwtCookie);

        UserVO existingUser = userService.findUserByEmailandProvider(userInfo.getEmail(), userInfo.getProvider());

        if (existingUser == null) {
            // 사용자가 존재하지 않으면 DB에 저장
            UserVO newUser = new UserVO();
            newUser.setEmail(userInfo.getEmail());
            newUser.setName(userInfo.getNickname());
            newUser.setProvider(userInfo.getProvider());
            newUser.setRole("ROLE_USER");  // 기본 역할 설정
            userService.insertUser(newUser);

            response.sendRedirect("http://localhost:5173/register");
        }
        else {
            // 프론트엔드로 리다이렉트
            response.sendRedirect("http://localhost:5173/myroutine");
        }
    }


    @GetMapping("/naver")
    public void successNaverLogin(
            @RequestParam("code") String code,
            HttpServletResponse response
    ) throws IOException {
        ResponseEntity<String> naverResponse = naverOauth2Service.getUserProfile(code);

        NaverUserInfo userInfo = naverOauth2Service.getNaverUserInfo(naverResponse);

        String token = jwtUtil.generateToken(userInfo.getEmail(), userInfo.getProvider(), "ROLE_USER");
        // JWT 토큰을 쿠키에 담아서 설정
        Cookie jwtCookie = cookieUtil.generateCookie(token);
        // 쿠키를 응답에 추가
        response.addCookie(jwtCookie);

        UserVO existingUser = userService.findUserByEmailandProvider(userInfo.getEmail(), userInfo.getProvider());

        if (existingUser == null) {
            // 사용자가 존재하지 않으면 DB에 저장
            UserVO newUser = new UserVO();
            newUser.setEmail(userInfo.getEmail());
            newUser.setName(userInfo.getNickname());
            newUser.setProvider(userInfo.getProvider());
            newUser.setRole("ROLE_USER");  // 기본 역할 설정
            userService.insertUser(newUser);

            response.sendRedirect("http://localhost:5173/register");
        } else {
            // 프론트엔드로 리다이렉트
            response.sendRedirect("http://localhost:5173/myroutine");
        }
    }

}
