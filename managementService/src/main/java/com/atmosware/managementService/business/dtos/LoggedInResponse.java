package com.atmosware.managementService.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInResponse {
    private String accessToken;
}
