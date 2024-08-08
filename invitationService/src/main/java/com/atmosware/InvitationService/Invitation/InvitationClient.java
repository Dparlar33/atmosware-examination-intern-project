package com.atmosware.InvitationService.Invitation;

import net.devh.boot.grpc.client.inject.GrpcClient;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

public class InvitationClient implements InvitationAdapter{

    // Client inject
    @GrpcClient("managementService")
    InvitationServiceGrpc.InvitationServiceBlockingStub invitationServiceBlockingStub;

    public SentMailResponse getFromClient(SendMailRequest request) {
        return this.invitationServiceBlockingStub.sendMail(request);
    }
}
