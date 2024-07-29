package com.atmosware.managementService.business.abstracts;

import com.atmosware.managementService.business.dtos.requests.organization.CreateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.organization.UpdateOrganizationRequest;
import com.atmosware.managementService.business.dtos.responses.organization.GetAllOrganizationResponse;

import java.util.List;

public interface OrganizationService {
    void addOrganization(CreateOrganizationRequest createOrganizationRequest);
    void updateOrganization(UpdateOrganizationRequest updateOrganizationRequest);
    List<GetAllOrganizationResponse> getAllOrganizations();
}
