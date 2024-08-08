package com.atmosware.InvitationService.Invitation;

import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

public interface InvitationAdapter {
    SentMailResponse getFromClient(SendMailRequest request);
}
