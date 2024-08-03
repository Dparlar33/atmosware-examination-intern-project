package com.atmosware.InvitationService.api.controller;


import com.atmosware.InvitationService.business.InvitationService;
import com.atmosware.common.invitation.InvitationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation-service/api/v1/invitation")
@AllArgsConstructor
public class InvitationController {
    private InvitationService invitationService;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public void sendInvitation(@RequestBody InvitationRequest invitationRequest) {
         this.invitationService.sendInvitationMail(invitationRequest);
    }
}
