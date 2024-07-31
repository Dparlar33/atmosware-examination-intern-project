package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    void register(RegisterRequest request);
    GetUserByIdResponse findUserById(UUID id);
    void updateUser(UpdateUserRequest updateUserRequest);
    boolean isUserAnOrganizationByUserId(UUID userId);
    boolean isUserAnAdminByUserId(UUID userId);
}
