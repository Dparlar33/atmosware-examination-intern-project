package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.business.messages.UserMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.UserMapper;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserManager implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleService userRoleService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @Override
    public void register(RegisterRequest request) {
        userEmailCanNotBeDuplicated(request.getEmail());

        User user = this.userMapper.registerRequestToUser(request);

        String encodedPassword = this.passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        this.userRepository.save(user);

        Role role = this.roleService.getRoleById(request.getRoleId());
        this.userRoleService.addUserRole(user,role);
    }

    @Override
    public GetUserByIdResponse findUserById(UUID id) {
        User user = isUserExistById(id);
        return  this.userMapper.userToGetUserById(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        isUserExistByEmail(updateUserRequest.getEmail());

        User user = this.userMapper.updateUserRequestToUser(updateUserRequest);

        String encodedPassword = passwordEncoder.encode(updateUserRequest.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(AuthMessages.LOGIN_FAILED));
    }

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
}
