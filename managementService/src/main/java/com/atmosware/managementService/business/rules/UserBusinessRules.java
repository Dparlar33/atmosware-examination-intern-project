package com.atmosware.managementService.business.rules;

import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBusinessRules {

    private final UserRepository userRepository;

    public void userEmailCanNotBeDuplicated(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new BusinessException("User already exists");
        }
    }

   public void isUserExistByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new BusinessException("User not found");
        }
    }

}
