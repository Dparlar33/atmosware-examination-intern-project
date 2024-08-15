package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import com.atmosware.managementService.business.messages.RoleMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.RoleMapper;
import com.atmosware.managementService.dataAccess.RoleRepository;
import com.atmosware.managementService.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {

    private RoleRepository roleRepository;
    private RoleMapper roleMapper;

    @Override
    public String getRoleNameById(UUID id) {
        Role role = isRoleExistById(id);
        return role.getName();
    }

    @Override
    public Role getRoleById(UUID id){
        return isRoleExistById(id);
    }


    @Override
    public List<GetAllRolesResponse> getAllRoles() {
        List<Role> roles = this.roleRepository.findAll();

        return roles.stream().map(role -> roleMapper.roleToGetAllRolesResponse(role)).toList();
    }

    public Role isRoleExistById(UUID id) {
        Optional<Role> role = this.roleRepository.findById(id);

        if (role.isEmpty()) {
            throw new BusinessException(RoleMessages.ROLE_NOT_FOUND);
        }

        return role.get();
    }
}
