package com.ownerofglory.events.listener;


import com.ownerofglory.events.OnRegistrationCompleteEvent;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    private final JavaMailSender mailSender;

    private final SystemService systemService;

    @Value("${verification.mail.subject}")
    private String mailSubject;

    @Value("${verification.mail.message-template}")
    private String mailTemplate;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        UserDTO user = event.getUser();

        String emailAddress = user.getUsername();
        String token = UUID.randomUUID().toString();
        systemService.createVerificationToken(user, token);

        String subject = mailSubject;
        String message = mailTemplate.replaceAll("TOKEN_TEMPLATE", token);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(message);
        //mailSender.send(email);
    }
}
