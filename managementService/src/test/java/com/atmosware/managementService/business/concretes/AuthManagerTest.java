package com.atmosware.managementService.business.concretes;

import com.atmosware.core.services.JwtService;
import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.dtos.requests.user.LoginRequest;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AuthManagerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private AuthManager authManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogin_Success() {
        String email = "test@example.com";
        String password = "password";
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        LoginRequest loginRequest = new LoginRequest(email, password);

        User user = new User();
        user.setId(userId);
        user.setEmail(email);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userRoleService.getRoleIdByUserId(userId)).thenReturn(roleId);
        when(roleService.getRoleNameById(roleId)).thenReturn("ROLE_USER");

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", email);
        claims.put("role", "ROLE_USER");

        String expectedJwt = "jwt-token";
        when(jwtService.generateToken(email, claims)).thenReturn(expectedJwt);

        String actualJwt = authManager.login(loginRequest);

        assertEquals(expectedJwt, actualJwt);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail(email);
        verify(userRoleService).getRoleIdByUserId(userId);
        verify(roleService).getRoleNameById(roleId);
        verify(jwtService).generateToken(email, claims);
    }

    @Test
    public void testLogin_Failure() {
        String email = "test@example.com";
        String password = "password";

        LoginRequest loginRequest = new LoginRequest(email, password);

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            authManager.login(loginRequest);
        });

        assertEquals(AuthMessages.LOGIN_FAILED, exception.getMessage());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository, never()).findByEmail(anyString());
        verify(userRoleService, never()).getRoleIdByUserId(any(UUID.class));
        verify(roleService, never()).getRoleNameById(any(UUID.class));
        verify(jwtService, never()).generateToken(anyString(), anyMap());
    }
}