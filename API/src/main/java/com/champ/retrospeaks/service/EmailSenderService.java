package com.champ.retrospeaks.service;

import com.champ.retrospeaks.model.User;

import javax.mail.MessagingException;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String message);
}