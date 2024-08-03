package com.atmosware.InvitationService.business;



import com.atmosware.InvitationService.core.LoggerService;
import com.atmosware.common.invitation.InvitationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationManager implements InvitationService {

    private final LoggerService logger;

    @Override
    public void sendInvitationMail(InvitationRequest invitationRequest) {
        logger.info("Invite email sent to : " + invitationRequest.getEmail());
    }
}
