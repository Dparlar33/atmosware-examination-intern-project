package com.atmosware.managementService.core.utilities.mapping;

import com.atmosware.managementService.business.dtos.requests.organization.CreateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.organization.UpdateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.organization.GetAllOrganizationResponse;
import com.atmosware.managementService.entities.Organization;
import org.mapstruct.Mapper;

@Mapper(config = MapStructureService.class)
public interface OrganizationMapper {

    Organization createOrganizationRequestToOrganization(CreateOrganizationRequest createOrganizationRequest);

    UpdateUserRequest organizationToUpdateUserRequest(Organization organization);

    RegisterRequest organizationToRegisterRequest(Organization organization);

    Organization updateOrganizationRequestToOrganization(UpdateOrganizationRequest updateOrganizationRequest);

    GetAllOrganizationResponse organizationToGetAllOrganizationResponse(Organization organization);

}
