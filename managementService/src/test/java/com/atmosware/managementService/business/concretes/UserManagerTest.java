package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.abstracts.UserRoleService;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.messages.AuthMessages;
import com.atmosware.managementService.business.messages.UserMessages;
import com.atmosware.managementService.core.utilities.exceptions.types.BusinessException;
import com.atmosware.managementService.core.utilities.mapping.UserMapper;
import com.atmosware.managementService.core.utilities.mapping.UserMapperImpl;
import com.atmosware.managementService.dataAccess.UserRepository;
import com.atmosware.managementService.entities.Role;
import com.atmosware.managementService.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserManagerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserManager userManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        UserMapper userMapper = new UserMapperImpl();
        userManager = new UserManager(userRepository, passwordEncoder, userRoleService, roleService, userMapper);
    }

    @Test
    public void testRegister_Success() {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@example.com");
        registerRequest.setPassword("password");
        registerRequest.setRoleId(UUID.randomUUID());

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(registerRequest.getEmail());
        user.setPassword("encodedPassword");

        Role role = new Role();
        role.setId(registerRequest.getRoleId());

        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(roleService.getRoleById(registerRequest.getRoleId())).thenReturn(role);

        userManager.register(registerRequest);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());

        User capturedUser = userCaptor.getValue();
        assertEquals(user.getEmail(), capturedUser.getEmail());
        assertEquals("encodedPassword", capturedUser.getPassword());

        verify(userRoleService).addUserRole(capturedUser, role);
    }

    @Test
    public void testFindUserById_Success() {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        GetUserByIdResponse response = userManager.findUserById(userId);

        assertNotNull(response);
        verify(userRepository).findById(userId);
    }

    @Test
    public void testUpdateUser_Success() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail("test@example.com");
        updateUserRequest.setPassword("newPassword");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail("test@example.com");
        user.setPassword("encodedNewPassword");


        when(userRepository.findByEmail(updateUserRequest.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.encode(updateUserRequest.getPassword())).thenReturn("encodedNewPassword");

        userManager.updateUser(updateUserRequest);

        // Argüman eşleştirme ile save() metodunun doğru nesneyi aldığından emin olun
        verify(userRepository).save(argThat(userArg ->
                userArg.getEmail().equals(user.getEmail()) &&
                        userArg.getPassword().equals(user.getPassword())
        ));
    }

    @Test
    public void testLoadUserByUsername_Success() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userManager.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userManager.loadUserByUsername(email);
        });

        assertEquals(AuthMessages.LOGIN_FAILED, exception.getMessage());
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testUserEmailCanNotBeDuplicated_Success() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        userManager.userEmailCanNotBeDuplicated(email);

        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testUserEmailCanNotBeDuplicated_EmailExists() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userManager.userEmailCanNotBeDuplicated(email);
        });

        assertEquals(UserMessages.USER_ALREADY_EXISTS, exception.getMessage());
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testIsUserExistByEmail_UserNotFound() {
        String email = "test@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userManager.isUserExistByEmail(email);
        });

        assertEquals(UserMessages.USER_NOT_FOUND, exception.getMessage());
        verify(userRepository).findByEmail(email);
    }

    @Test
    public void testIsUserExistById_UserNotFound() {
        UUID userId = UUID.randomUUID();

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        BusinessException exception = assertThrows(BusinessException.class, () -> {
            userManager.isUserExistById(userId);
        });

        assertEquals(UserMessages.USER_NOT_FOUND, exception.getMessage());
        verify(userRepository).findById(userId);
    }
}
