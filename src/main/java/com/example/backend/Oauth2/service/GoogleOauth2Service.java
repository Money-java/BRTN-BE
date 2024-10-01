package com.example.backend.Oauth2.service;

import com.example.backend.Oauth2.utils.GoogleUserInfo;
import com.example.backend.Oauth2.utils.KakaoUserInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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




    public GoogleUserInfo getGoogleUserInfo(ResponseEntity<String> response) throws JsonProcessingException {
        String responseBody = response.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String nickname = jsonNode.get("name").asText();
        String email = jsonNode.get("email").asText();

        return new GoogleUserInfo(email,nickname, "google");
    }

    //google code로 accessToken 받아오기
    @Override
    public ResponseEntity<String> getUserProfile(String code) {

        RestTemplate restTemplate = new RestTemplate();
        String accessCode = getAccessToken(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessCode);


        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v2/userinfo",
                HttpMethod.GET,
                request,
                String.class
        );

        if(response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return null;

    }

    @Override
    public String getAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();

        params.put("code", code);
        params.put("client_id", GOOGLE_CLIENT_ID);
        params.put("client_secret", GOOGLE_CLIENT_SECRET);
        params.put("redirect_uri", GOOGLE_REDIRECT_URL);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> response = restTemplate.postForEntity(GOOGLE_TOKEN_URL, params, String.class);

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
