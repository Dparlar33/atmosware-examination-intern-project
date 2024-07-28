package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.LoggedInResponse;
import com.atmosware.managementService.business.dtos.LoginRequest;
import com.atmosware.managementService.business.dtos.RegisterRequest;

public interface AuthService {
    String login(LoginRequest loginRequest);
}
