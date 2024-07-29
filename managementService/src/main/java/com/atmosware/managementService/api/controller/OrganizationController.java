package com.atmosware.managementService.api.controller;

import com.atmosware.managementService.business.abstracts.OrganizationService;
import com.atmosware.managementService.business.dtos.requests.organization.CreateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.organization.UpdateOrganizationRequest;
import com.atmosware.managementService.business.dtos.responses.organization.GetAllOrganizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management-service/api/v1/organizations")
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    @PostMapping("/add")
    public void addOrganization(@RequestBody CreateOrganizationRequest createOrganizationRequest) {
        this.organizationService.addOrganization(createOrganizationRequest);
    }

    @PutMapping("/update")
    public void updateOrganization(@RequestBody UpdateOrganizationRequest updateOrganizationRequest) {
        this.organizationService.updateOrganization(updateOrganizationRequest);
    }

    @GetMapping("/getAll")
    public List<GetAllOrganizationResponse> getAllOrganization() {
        return this.organizationService.getAllOrganizations();
    }
}
