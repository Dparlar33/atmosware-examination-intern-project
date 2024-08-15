package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.dataAccess.UserRoleRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import com.atmosware.managementService.entities.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserRoleManagerTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleManager userRoleManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddUserRole_Success() {
        User user = new User();
        user.setId(UUID.randomUUID());

        Role role = new Role();
        role.setId(UUID.randomUUID());

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        when(userRoleRepository.save(userRole)).thenReturn(new UserRole());

        userRoleManager.addUserRole(user, role);

        verify(userRoleRepository).save(any(UserRole.class));
    }

    @Test
    public void testGetRoleIdByUserId_Success() {
        UUID userId = UUID.randomUUID();
        UUID roleId = UUID.randomUUID();

        Role role = new Role();
        role.setId(roleId);

        UserRole userRole = new UserRole();
        userRole.setUser(new User());
        userRole.setRole(role);

        when(userRoleRepository.findUserRoleByUserId(userId)).thenReturn(userRole);

        UUID resultRoleId = userRoleManager.getRoleIdByUserId(userId);

        assertEquals(roleId, resultRoleId);
        verify(userRoleRepository).findUserRoleByUserId(userId);
    }
}