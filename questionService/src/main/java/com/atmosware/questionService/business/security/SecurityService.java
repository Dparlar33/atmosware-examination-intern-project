package com.atmosware.questionService.business.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityService {
    HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception;
}
