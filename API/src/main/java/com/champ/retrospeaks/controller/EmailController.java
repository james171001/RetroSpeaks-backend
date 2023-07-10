package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.model.EmailMessage;
import com.champ.retrospeaks.service.EmailSenderService;

import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    //    @Autowired
    private final UserService userService;
    private final EmailSenderService emailSenderService;

    @Autowired
    public EmailController(UserService userService, EmailSenderService emailSenderService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
    }

    @PostMapping("/send-email")
    public ResponseEntity sendEmail(@RequestBody EmailMessage emailMessage) {
        Long userId = emailMessage.getUserId();
        String userEmail = userService.getUserEmailById(userId);
        this.emailSenderService.sendEmail(userEmail, emailMessage.getSubject(), emailMessage.getMessage());
        return ResponseEntity.ok("Success");
    }
}