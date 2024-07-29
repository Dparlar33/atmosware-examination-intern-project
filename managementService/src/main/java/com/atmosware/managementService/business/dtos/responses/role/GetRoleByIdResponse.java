package com.atmosware.managementService.business.dtos.responses.role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GetRoleByIdResponse {
    private UUID id;
    private String name;
}
