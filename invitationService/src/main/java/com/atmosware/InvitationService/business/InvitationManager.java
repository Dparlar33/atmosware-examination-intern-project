package com.atmosware.InvitationService.business;



import com.atmosware.InvitationService.core.LoggerService;
import com.atmosware.common.invitation.InvitationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvitationManager implements InvitationService {

    private final LoggerService logger;
    private final JavaMailSender javaMailSender;

    @Override
    public void sendInvitationMail(InvitationRequest invitationRequest) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(invitationRequest.getEmail());
        message.setSubject(null);
        message.setText(invitationRequest.getUrl());
        this.javaMailSender.send(message);

        logger.info("Invite email sent to : " + invitationRequest.getEmail());
    }
}
