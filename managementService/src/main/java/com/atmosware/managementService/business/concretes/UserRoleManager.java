package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.dataAccess.UserRoleRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import com.atmosware.managementService.entities.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserRoleManager implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Override
    public void addUserRole(User user , Role role) {
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        this.userRoleRepository.save(userRole);
    }

    @Override
    public UUID getRoleByUser(User user) {
        UserRole userRole = this.userRoleRepository.findUserRoleByUser(user);
        return userRole.getRole().getId();
    }
}
