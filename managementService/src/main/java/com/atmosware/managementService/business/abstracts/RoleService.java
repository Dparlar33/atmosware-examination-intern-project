package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import com.atmosware.managementService.business.dtos.responses.role.GetRoleByIdResponse;
import com.atmosware.managementService.entities.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role getRoleById(UUID id);
    List<GetAllRolesResponse> getAllRoles();
}
