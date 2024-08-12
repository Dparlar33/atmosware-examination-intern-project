package com.atmosware.managementService.api.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;


@Service
public class InvitationClientService {

    @GrpcClient("invitationService")
    InvitationServiceGrpc.InvitationServiceBlockingStub blockingStub;

    ManagedChannel channel  = ManagedChannelBuilder.
            forAddress("localhost", 10076).
            usePlaintext().
            build();

    public SentMailResponse sendMail(SendMailRequest request){
        blockingStub = InvitationServiceGrpc.newBlockingStub(channel);
        return this.blockingStub.sendMail(request);
    }
}
