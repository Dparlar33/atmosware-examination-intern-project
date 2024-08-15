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
        // Prepare request and response
        SendMailRequest request = SendMailRequest.newBuilder()
                .setEmail("dp@gmail.com")
                .setDescription("Sinav URL")
                .setUrl("www.google.com")
                .build();

        SentMailResponse expectedResponse = SentMailResponse.newBuilder()
                .setMessage("Email Sent")
                .build();

        // Mock the service method
        when(invitationClientService.sendMail(request)).thenReturn(expectedResponse);

        // Call the method under test
        invitationGrpcManager.sendMail(request, responseObserver);

        // Verify interactions and responses
        verify(responseObserver).onNext(expectedResponse);
        verify(responseObserver).onCompleted();
    }

    @Test
    public void testSendMail_Failure() {
        // Prepare request and simulate a failure response
        SendMailRequest request = SendMailRequest.newBuilder()
                .setEmail("dp@gmail.com")
                .setDescription("Sinav URL")
                .setUrl("www.google.com")
                .build();

        RuntimeException exception = new RuntimeException("Sending email failed");

        // Mock the service method to throw an exception
        when(invitationClientService.sendMail(request)).thenThrow(exception);

        // Call the method under test and handle the exception
        assertThrows(RuntimeException.class, () -> {
            invitationGrpcManager.sendMail(request, responseObserver);
        });

        // Verify that no response is sent in case of failure
        verify(responseObserver, never()).onNext(any());
        verify(responseObserver, never()).onCompleted();
    }
}