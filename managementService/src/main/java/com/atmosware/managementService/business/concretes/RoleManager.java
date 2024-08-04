package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.rules.RoleBusinessRules;
import com.atmosware.managementService.core.utilities.mapping.RoleMapper;
import com.atmosware.managementService.core.utilities.mapping.UserMapper;
import com.atmosware.managementService.dataAccess.RoleRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {

    private RoleRepository roleRepository;
    private RoleBusinessRules roleBusinessRules;
    private RoleMapper roleMapper;

    @Override
    public String getRoleNameById(UUID id) {
        this.roleBusinessRules.isRoleExistById(id);
        Role role = this.roleRepository.findById(id).orElse(null);
        assert role != null;
        return role.getName();
    }

    @Override
    public Role getRoleById(UUID id){
        return this.roleBusinessRules.isRoleExistById(id);
    }


    @Override
    public List<GetAllRolesResponse> getAllRoles() {
        List<Role> roles = this.roleRepository.findAll();

        return roles.stream().map(role -> roleMapper.roleToGetAllRolesResponse(role)).toList();
    }
}
