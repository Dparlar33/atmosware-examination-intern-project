package com.atmosware.examService.core.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityService {
    HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception;
}
