package com.cs.bug24.bug24_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())          // CSRF 끄기 (API 개발 단계)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()      // 모든 요청 허용
                )
                .httpBasic(Customizer.withDefaults());  // 기본 설정 (원하면 .disable()도 가능)

        return http.build();
    }
}
