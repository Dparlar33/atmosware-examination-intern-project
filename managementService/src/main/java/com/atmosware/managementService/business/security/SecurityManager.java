package com.atmosware.managementService.business.security;

import com.atmosware.core.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableWebSecurity
public class SecurityManager implements SecurityService {

    private final JwtAuthFilter jwtAuthFilter;

    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/v1/auth/**",
            "/management-service/api/v1/users/**",
            "/management-service/api/v1/roles/getAll",
            "/management-service/api/v1/invitation/send"
    };


    //Authorization processes will be done last.
    // Currently, in the development environment, all requests can be made without requiring a role and JWT.
    @Override
    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req.requestMatchers(HttpMethod.POST,"/management-service/api/v1/auth/login").permitAll())
                .authorizeHttpRequests(req-> req.requestMatchers(WHITE_LIST_URLS).permitAll().anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity;
    }
}
