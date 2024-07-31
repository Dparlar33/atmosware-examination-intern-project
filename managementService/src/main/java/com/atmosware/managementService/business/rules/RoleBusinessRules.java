package com.atmosware.managementService.business.rules;

import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.messages.RoleMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.UserMapper;
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

    public Role isRoleExistById(UUID id) {
        Optional<Role> role = this.roleRepository.findById(id);

        if (role.isEmpty()) {
            throw new BusinessException(RoleMessages.ROLE_NOT_FOUND);
        }

        return role.get();
    }

}
