package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.api.grpc.InvitationClientService;
import com.atmosware.managementService.business.abstracts.InvitationGrpcService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

@GrpcService
@RequiredArgsConstructor
public class InvitationGrpcManager extends InvitationServiceGrpc.InvitationServiceImplBase implements InvitationGrpcService {

    private final InvitationClientService invitationClientService;

    @Override
    public void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver) {
        SentMailResponse response = this.invitationClientService.sendMail(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
