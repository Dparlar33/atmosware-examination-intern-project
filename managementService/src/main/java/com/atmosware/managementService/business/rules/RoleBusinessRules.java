package com.atmosware.managementService.business.rules;

import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.messages.RoleMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.dataAccess.RoleRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RoleBusinessRules {

    private RoleRepository roleRepository;
    private UserRoleService userRoleService;

    public Role isRoleExistById(UUID id){
        Optional<Role> role =  this.roleRepository.findById(id);

        if(role.isEmpty()){
            throw new BusinessException(RoleMessages.ROLE_NOT_FOUND);
        }

        return role.get();
    }

    public boolean checkIsRoleOrganization(User user){
       UUID roleId =  this.userRoleService.getRoleByUser(user);

       Role role = isRoleExistById(roleId);

       return role.getId().equals(UUID.fromString("fc2f7c0e-5dc8-41c8-bb83-e9bde91aed56"));
    }

    public boolean checkIsRoleAdmin(User user){
        UUID roleId =  this.userRoleService.getRoleByUser(user);

        Role role = isRoleExistById(roleId);

        return role.getId().equals(UUID.fromString("47634fc5-ea21-4319-acfc-856c5bad8abf"));
    }
}
