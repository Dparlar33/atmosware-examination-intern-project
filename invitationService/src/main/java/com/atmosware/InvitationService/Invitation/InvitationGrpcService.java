package com.atmosware.InvitationService.Invitation;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.LoggerFactory;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

@GrpcService
@RequiredArgsConstructor
@Slf4j
public class InvitationGrpcService extends InvitationServiceGrpc.InvitationServiceImplBase {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvitationGrpcService.class);
    // private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver) {

        final String invitedMessage = "Invite email sent to : " + request.getEmail();
        InvitationGrpcService.log.info(invitedMessage);

        final var response = SentMailResponse.newBuilder().setMessage(invitedMessage).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
