package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // 테스트 환경에서는 CSRF를 비활성화할 수 있습니다.
                .authorizeRequests()
                .antMatchers("/post-community/**").authenticated() // 인증이 필요한 경로 설정
                .and()
                .httpBasic(); // 간단한 Basic Auth 사용 (브라우저에서 기본 로그인 팝업 제공)
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 하드코딩된 테스트 사용자 설정
        UserDetails user = User.withUsername("Money.java")
                .password("{noop}password") // {noop}은 비밀번호 암호화 없이 사용한다는 뜻
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user); // 메모리 내에 사용자 저장
    }
}

