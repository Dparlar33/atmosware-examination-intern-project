package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.RegisterRequest;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.UserMapper;
import com.atmosware.managementService.dataAccess.UserRepository;
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

    @Override
    public void register(RegisterRequest request) {
        User user = UserMapper.INSTANCE.registerRequestToUser(request);
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    @Override
    public GetUserByIdResponse findUserById(UUID id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) {
            throw new BusinessException("User not found");
        }
        return  UserMapper.INSTANCE.userToGetUserById(user.get());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BusinessException(AuthMessages.LOGIN_FAILED));
    }

}
