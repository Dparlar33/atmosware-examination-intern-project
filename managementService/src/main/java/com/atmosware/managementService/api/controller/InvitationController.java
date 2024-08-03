package com.atmosware.managementService.api.controller;

import com.atmosware.common.invitation.InvitationRequest;
import com.atmosware.managementService.api.client.InvitationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/management-service/api/v1/invitation")
@RequiredArgsConstructor
public class InvitationController {

    private final InvitationClient invitationClient;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody InvitationRequest invitationRequest)
    {
        this.invitationClient.sendInvitation(invitationRequest);
    }
}
