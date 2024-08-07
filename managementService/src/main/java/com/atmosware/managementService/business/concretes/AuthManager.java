package com.atmosware.managementService.business.concretes;


import com.atmosware.core.services.JwtService;
import com.atmosware.managementService.business.abstracts.AuthService;
import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.dtos.requests.user.LoginRequest;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;
    private final RoleService roleService;

    @Override
    public String login(LoginRequest loginRequest) {

        Authentication authentication = this.authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        if (!authentication.isAuthenticated()) {
            throw new BusinessException(AuthMessages.LOGIN_FAILED);
        }

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        return generateJwt(user.get());
    }

    private String generateJwt(User user) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getEmail());

        UUID roleId = this.userRoleService.getRoleIdByUserId(user.getId());
        String role = this.roleService.getRoleNameById(roleId);
        claims.put("role", role);

        return this.jwtService.generateToken(user.getEmail(),claims);
    }
}
