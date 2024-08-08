package com.atmosware.managementService.api.grpc;

import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

@Service
public class InvitationClientService {

    @GrpcClient("invitationService")
    InvitationServiceGrpc.InvitationServiceBlockingStub blockingStub;

    public SentMailResponse sendMail(SendMailRequest request){
        return this.blockingStub.sendMail(request);
    }
}
