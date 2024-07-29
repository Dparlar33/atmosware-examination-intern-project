package com.atmosware.managementService.api.controller;

import com.atmosware.managementService.business.abstracts.AuthService;
import com.atmosware.managementService.business.dtos.requests.user.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/management-service/api/v1/login")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginRequest request)
    {
        return authService.login(request);
    }
}
