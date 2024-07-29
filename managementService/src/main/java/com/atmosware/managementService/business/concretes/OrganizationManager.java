package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.business.abstracts.OrganizationService;
import com.atmosware.managementService.business.abstracts.UserService;
import com.atmosware.managementService.business.dtos.requests.organization.CreateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.organization.UpdateOrganizationRequest;
import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.organization.GetAllOrganizationResponse;
import com.atmosware.managementService.core.utilities.mapping.OrganizationMapper;
import com.atmosware.managementService.dataAccess.OrganizationRepository;
import com.atmosware.managementService.entities.Organization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@AllArgsConstructor
public class OrganizationManager implements OrganizationService {

    private OrganizationRepository organizationRepository;
    private UserService userService;
    private OrganizationMapper organizationMapper;

    @Override
    @Transactional
    public void addOrganization(CreateOrganizationRequest createOrganizationRequest) {
        Organization organization = this.organizationMapper.createOrganizationRequestToOrganization(createOrganizationRequest);
        this.organizationRepository.save(organization);

        RegisterRequest request = this.organizationMapper.organizationToRegisterRequest(organization);
        this.userService.register(request);
    }

    @Override
    @Transactional
    public void updateOrganization(UpdateOrganizationRequest updateOrganizationRequest) {
        Organization organization = this.organizationMapper.updateOrganizationRequestToOrganization(updateOrganizationRequest);
        this.organizationRepository.save(organization);

        UpdateUserRequest request = this.organizationMapper.organizationToUpdateUserRequest(organization);
        this.userService.updateUser(request);
    }

    @Override
    public List<GetAllOrganizationResponse> getAllOrganizations() {
        List<Organization> organizations = this.organizationRepository.findAll();
        return organizations.stream().map(this.organizationMapper::organizationToGetAllOrganizationResponse).toList();
    }
}
