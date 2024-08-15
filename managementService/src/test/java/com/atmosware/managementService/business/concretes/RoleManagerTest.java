package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import com.atmosware.managementService.business.messages.RoleMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.RoleMapper;
import com.atmosware.managementService.dataAccess.RoleRepository;
import com.atmosware.managementService.entities.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoleManagerTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleManager roleManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRoleNameById_Success() {
        UUID roleId = UUID.randomUUID();
        Role role = new Role();
        role.setId(roleId);
        role.setName("ADMIN");

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        String roleName = roleManager.getRoleNameById(roleId);

        assertEquals("ADMIN", roleName);
        verify(roleRepository).findById(roleId);
    }

    @Test
    public void testGetRoleById_Success() {
        UUID roleId = UUID.randomUUID();
        Role role = new Role();
        role.setId(roleId);

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role result = roleManager.getRoleById(roleId);

        assertEquals(role, result);
        verify(roleRepository).findById(roleId);
    }

    @Test
    public void testGetAllRoles_Success() {
        Role role1 = new Role();
        role1.setId(UUID.randomUUID());
        role1.setName("ADMIN");

        Role role2 = new Role();
        role2.setId(UUID.randomUUID());
        role2.setName("USER");

        List<Role> roles = List.of(role1, role2);
        GetAllRolesResponse response1 = new GetAllRolesResponse(role1.getId(), role1.getName());
        GetAllRolesResponse response2 = new GetAllRolesResponse(role2.getId(), role2.getName());

        when(roleRepository.findAll()).thenReturn(roles);
        when(roleMapper.roleToGetAllRolesResponse(role1)).thenReturn(response1);
        when(roleMapper.roleToGetAllRolesResponse(role2)).thenReturn(response2);

        List<GetAllRolesResponse> responses = roleManager.getAllRoles();

        assertEquals(2, responses.size());
        assertEquals("ADMIN", responses.get(0).getName());
        assertEquals("USER", responses.get(1).getName());

        verify(roleRepository).findAll();
        verify(roleMapper).roleToGetAllRolesResponse(role1);
        verify(roleMapper).roleToGetAllRolesResponse(role2);
    }

    @Test
    public void testIsRoleExistById_RoleExists() {
        UUID roleId = UUID.randomUUID();
        Role role = new Role();
        role.setId(roleId);

        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        Role result = roleManager.isRoleExistById(roleId);

        assertEquals(role, result);
        verify(roleRepository).findById(roleId);
    }

    @Test
    public void testIsRoleExistById_RoleDoesNotExist() {
        UUID roleId = UUID.randomUUID();

        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            roleManager.isRoleExistById(roleId);
        });

        assertEquals(RoleMessages.ROLE_NOT_FOUND, exception.getMessage());
        verify(roleRepository).findById(roleId);
    }
}