package com.atmosware.managementService.api.controller;

import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.GetAllUsersResponse;
import com.atmosware.managementService.business.dtos.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.RegisterRequest;
import com.atmosware.managementService.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/getById/{id}")
    public GetUserByIdResponse getById(@PathVariable UUID id) {
        return this.userService.findUserById(id);
    }
}
