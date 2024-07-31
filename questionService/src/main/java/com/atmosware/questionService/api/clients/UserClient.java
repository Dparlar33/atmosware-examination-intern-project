package com.atmosware.questionService.api.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "managementService", url = "http://localhost:10044/management-service/api/v1/users/")
public interface UserClient {
    @GetMapping("/organization/{userId}")
    boolean isUserAnOrganizationByUserId(@PathVariable UUID userId);

    @GetMapping("/admin/{userId}")
    boolean isUserAnAdminByUserId(@PathVariable UUID userId);
}
