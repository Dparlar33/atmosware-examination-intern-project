package com.atmosware.examService.core.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableWebSecurity
public class SecurityManager implements SecurityService {


    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/auth/**",
            "/exam-service/api/v1/exams/**",
            "/actuator/prometheus"
    };

    @Override
    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req.requestMatchers(WHITE_LIST_URLS).permitAll().anyRequest().authenticated());

        return httpSecurity;
    }
}

