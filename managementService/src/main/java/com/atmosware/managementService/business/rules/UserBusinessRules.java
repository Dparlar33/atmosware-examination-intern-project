package com.atmosware.managementService.business.rules;

import com.atmosware.managementService.business.messages.UserMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void userEmailCanNotBeDuplicated(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException(UserMessages.USER_ALREADY_EXISTS);
        }
    }

   public void isUserExistByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BusinessException(UserMessages.USER_NOT_FOUND);
        }
    }

    public User isUserExistById(UUID id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isEmpty()) {
            throw new BusinessException(UserMessages.USER_NOT_FOUND);
        }
        return user.get();
    }

    public boolean checkIsRoleOrganization(Role role) {
        return role.getId().equals(UUID.fromString("fc2f7c0e-5dc8-41c8-bb83-e9bde91aed56"));
    }

    public boolean checkIsRoleAdmin(Role role) {
        return role.getId().equals(UUID.fromString("47634fc5-ea21-4319-acfc-856c5bad8abf"));
    }

}
