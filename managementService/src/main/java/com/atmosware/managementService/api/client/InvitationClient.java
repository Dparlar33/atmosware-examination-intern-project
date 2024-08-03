package com.atmosware.managementService.api.client;

import com.atmosware.common.invitation.InvitationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "invitationService", url = "http://localhost:10055/invitation-service/api/v1/invitation")
public interface InvitationClient {
    @PostMapping("/send")
    void sendInvitation(@RequestBody InvitationRequest invitationRequest);
}
