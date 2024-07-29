package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import com.atmosware.managementService.entities.UserRole;

import java.util.UUID;

public interface UserRoleService {
    void addUserRole(User user , Role role);
    void getUserRolesByUserId(UUID userId);
    void getRoleByUserId(UUID userId);
    void updateUserRole(UUID id ,UUID userId, UUID roleId);
}
