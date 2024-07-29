package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.requests.user.LoginRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
