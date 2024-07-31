package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.business.rules.UserBusinessRules;
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
    private final UserBusinessRules userBusinessRules;
    private final UserMapper userMapper;

    @Override
    public void register(RegisterRequest request) {
        this.userBusinessRules.userEmailCanNotBeDuplicated(request.getEmail());

        User user = this.userMapper.registerRequestToUser(request);

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        Role role = this.roleService.getRoleById(request.getRoleId());
        this.userRoleService.addUserRole(user,role);
    }

    @Override
    public GetUserByIdResponse findUserById(UUID id) {
        User user = this.userBusinessRules.isUserExistById(id);
        return  this.userMapper.userToGetUserById(user);
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        this.userBusinessRules.isUserExistByEmail(updateUserRequest.getEmail());

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

    @Override
    public boolean isUserAnOrganizationByUserId(UUID userId) {
        GetUserByIdResponse response = findUserById(userId);

        User user = this.userMapper.getUserByIdToUser(response);

        UUID roleId = this.userRoleService.getRoleByUser(user);

        Role role = this.roleService.getRoleById(roleId);

        return this.userBusinessRules.checkIsRoleOrganization(role);
    }

    @Override
    public boolean isUserAnAdminByUserId(UUID userId) {
        GetUserByIdResponse response = findUserById(userId);

        User user = this.userMapper.getUserByIdToUser(response);

        UUID roleId = this.userRoleService.getRoleByUser(user);

        Role role = this.roleService.getRoleById(roleId);

        return this.userBusinessRules.checkIsRoleAdmin(role);
    }

}
