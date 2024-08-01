package com.atmosware.managementService.api.controller;

import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/management-service/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        userService.register(request);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.updateUser(updateUserRequest);
    }

    @GetMapping("/getById/{id}")
    public GetUserByIdResponse getById(@PathVariable UUID id) {
        return this.userService.findUserById(id);
    }

}
