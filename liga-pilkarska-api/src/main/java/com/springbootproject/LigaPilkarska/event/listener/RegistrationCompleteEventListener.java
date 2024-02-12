package com.springbootproject.LigaPilkarska.event.listener;

import com.springbootproject.LigaPilkarska.entity.User;
import com.springbootproject.LigaPilkarska.event.RegistrationCompleteEvent;
import com.springbootproject.LigaPilkarska.service.EmailSenderService;
import com.springbootproject.LigaPilkarska.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@DependsOn("emailSenderService")
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;
    private final EmailSenderService emailSenderService;

    public RegistrationCompleteEventListener(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {

        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);

        String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;


        emailSenderService.sendEmail(user.getEmail(), "Your verification email", "Click the link to verify your account: " + url);
        log.info("Click the link to verify your account {}", url);
    }
}
