package com.example.backend.Oauth2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Service
@PropertySource("classpath:application.properties")
public class KakaoOauth2Service implements CustomOauth2Service{

    @Value("${kakao.token-url}")
    private String KAKAO_TOKEN_URL;
    @Value("${kakao.client-id}")
    private String KAKAO_CLIENT_ID;
    @Value("${kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;
    @Value("${kakao.redirect-url}")
    private String KAKAO_REDIRECT_URL;

    @Override
    public ResponseEntity<String> getUserInfo(String accessCode) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessCode);


        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                request,
                String.class
        );

        if(response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return null;

    }

    //AccessToken을 받아오는 메소드
    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        //kakao는 application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 바디 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", KAKAO_CLIENT_ID);
        params.add("redirect_uri", KAKAO_REDIRECT_URL);
        params.add("code", code);
        params.add("client_secret", KAKAO_CLIENT_SECRET);

        // 요청 엔티티 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(KAKAO_TOKEN_URL, HttpMethod.POST, requestEntity, String.class);

        if(response.getStatusCode() == HttpStatus.OK){
            try {
                // Jackson ObjectMapper를 이용해 JSON 파싱
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(response.getBody());

                // access_token 추출
                String accessToken = rootNode.path("access_token").asText();
                return accessToken;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 오류가 발생하면 null 반환
        return null;
    }


}
