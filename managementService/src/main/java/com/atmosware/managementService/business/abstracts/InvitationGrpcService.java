package com.atmosware.managementService.business.abstracts;

import io.grpc.stub.StreamObserver;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

public interface InvitationGrpcService {
    void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver);
}
