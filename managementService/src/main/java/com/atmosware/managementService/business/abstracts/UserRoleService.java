package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;

import java.util.UUID;

public interface UserRoleService {
    void addUserRole(User user , Role role);
    UUID getRoleIdByUserId(UUID userId);
}
