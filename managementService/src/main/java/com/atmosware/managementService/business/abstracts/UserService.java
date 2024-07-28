package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.GetAllUsersResponse;
import com.atmosware.managementService.business.dtos.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.RegisterRequest;
import com.atmosware.managementService.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService extends UserDetailsService {
    void register(RegisterRequest request);
    GetUserByIdResponse findUserById(UUID id);
}
