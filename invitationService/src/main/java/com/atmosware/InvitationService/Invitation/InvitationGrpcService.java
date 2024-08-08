package com.atmosware.InvitationService.Invitation;

import com.atmosware.InvitationService.core.LoggerService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import tr.com.atmosware.invitation.InvitationServiceGrpc;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

@GrpcService
@RequiredArgsConstructor
public class InvitationGrpcService extends InvitationServiceGrpc.InvitationServiceImplBase {

    private final LoggerService logger;
   // private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(SendMailRequest request, StreamObserver<SentMailResponse> responseObserver) {
        //SimpleMailMessage message = new SimpleMailMessage();

       /* message.setTo(request.getEmail());
        message.setSubject(null);
        message.setText(request.getUrl());
        this.javaMailSender.send(message);*/

        final String invitedMessage = "Invite email sent to : " + request.getEmail();
        logger.info(invitedMessage);

        final var response = SentMailResponse.newBuilder().setMessage(invitedMessage).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
