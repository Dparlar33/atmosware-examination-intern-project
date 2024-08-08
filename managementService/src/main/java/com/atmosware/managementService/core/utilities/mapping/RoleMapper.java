package com.atmosware.managementService.core.utilities.mapping;

import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import com.atmosware.managementService.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    GetAllRolesResponse roleToGetAllRolesResponse(Role role);
}
