package com.example.backend.oauth2.service;

import com.example.backend.oauth2.dto.NaverUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


@Component
@PropertySource("classpath:application.properties")
public class NaverOauth2Service implements CustomOauth2Service{

    @Value("${naver.token-url}")
    private String NAVER_TOKEN_URL;
    @Value("${naver.client-id}")
    private String NAVER_CLIENT_ID;
    @Value("${naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    public NaverUserInfo getNaverUserInfo(ResponseEntity<String> response) throws JsonProcessingException {

        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String nickname = jsonNode.get("response").get("name").asText();
        String email = jsonNode.get("response").get("email").asText();

        return new NaverUserInfo(email,nickname, "naver");
    }


    @Override
    public ResponseEntity<String> getUserProfile(String code) {
        RestTemplate restTemplate = new RestTemplate();
        String accessCode = getAccessToken(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessCode);


        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                request,
                String.class
        );

        if(response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return null;
    }



    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        // 헤더 설정
        //kakao는 application/x-www-form-urlencoded
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 바디 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", NAVER_CLIENT_ID);
        params.add("client_secret", NAVER_CLIENT_SECRET);
        params.add("code", code);

        // 요청 엔티티 생성
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);

        // 요청 전송
        ResponseEntity<String> response = restTemplate.exchange(NAVER_TOKEN_URL, HttpMethod.POST, requestEntity, String.class);

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
