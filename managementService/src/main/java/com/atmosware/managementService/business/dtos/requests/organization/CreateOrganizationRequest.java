package com.atmosware.managementService.business.dtos.requests.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrganizationRequest {
    private UUID roleId;
    private String name;
    private String email;
    private String password;
}
