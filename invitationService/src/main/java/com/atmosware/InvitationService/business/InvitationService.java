package com.atmosware.InvitationService.business;


import com.atmosware.common.invitation.InvitationRequest;

public interface InvitationService {
    void sendInvitationMail(InvitationRequest invitationRequest);
}
