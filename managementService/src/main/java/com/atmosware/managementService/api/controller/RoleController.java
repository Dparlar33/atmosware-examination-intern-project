package com.atmosware.managementService.api.controller;

import com.atmosware.managementService.business.abstracts.RoleService;
import com.atmosware.managementService.business.dtos.responses.role.GetAllRolesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/management-service/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/getAll")
    public List<GetAllRolesResponse> getAll() {
        return this.roleService.getAllRoles();
    }

    @GetMapping("/organization/{userId}")
    public boolean isUserAnOrganizationByUserId(@PathVariable UUID userId){
        return this.roleService.isUserAnOrganizationByUserId(userId);
    }

    @GetMapping("/admin/{userId}")
    public boolean isUserAnAdminByUserId(@PathVariable UUID userId){
        return this.roleService.isUserAnAdminByUserId(userId);
    }
}
