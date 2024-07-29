package com.atmosware.managementService.business.dtos.requests.organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrganizationRequest {
    private UUID id;
    private UUID roleId;
    private String name;
    private String email;
    private String password;
    private List<String> candidateEmailList;
}
