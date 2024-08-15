package com.atmosware.managementService.business.concretes;

import com.atmosware.managementService.api.grpc.InvitationClientService;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.com.atmosware.invitation.SendMailRequest;
import tr.com.atmosware.invitation.SentMailResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class InvitationGrpcManagerTest {

    @Mock
    private InvitationClientService invitationClientService;

    @Mock
    private StreamObserver<SentMailResponse> responseObserver;

    @InjectMocks
    private InvitationGrpcManager invitationGrpcManager;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMail_Success() {
        SendMailRequest request = SendMailRequest.newBuilder()
                .setEmail("dp@gmail.com")
                .setDescription("Sinav URL")
                .setUrl("www.google.com")
                .build();

        SentMailResponse expectedResponse = SentMailResponse.newBuilder()
                .setMessage("Email Sent")
                .build();


        when(invitationClientService.sendMail(request)).thenReturn(expectedResponse);


        invitationGrpcManager.sendMail(request, responseObserver);

        verify(responseObserver).onNext(expectedResponse);
        verify(responseObserver).onCompleted();
    }

    @Test
    public void testSendMail_Failure() {

        SendMailRequest request = SendMailRequest.newBuilder()
                .setEmail("dp@gmail.com")
                .setDescription("Sinav URL")
                .setUrl("www.google.com")
                .build();

        RuntimeException exception = new RuntimeException("Sending email failed");


        when(invitationClientService.sendMail(request)).thenThrow(exception);


        assertThrows(RuntimeException.class, () -> {
            invitationGrpcManager.sendMail(request, responseObserver);
        });


        verify(responseObserver, never()).onNext(any());
        verify(responseObserver, never()).onCompleted();
    }
}