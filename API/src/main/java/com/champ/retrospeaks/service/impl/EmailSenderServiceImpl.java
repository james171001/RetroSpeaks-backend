package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
@Service
@Transactional
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender mailSender;

    public EmailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("retrospeaks.service2023@gmail.com");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendAutoGeneratedPassword(User user) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject("Auto-generated Password");

        String message = "Good day!<br><br>" +
                "Here's your log in credentials:<br>" +
                "Username: <strong>" + user.getUsername() + "</strong><br>" +
                "Your auto-generated password is: <strong>" + user.getPassword() + "</strong><br><br>" +
                "Please use this password to log in initially to the system.<br>" +
                "Alternatively, you can click <a href=\"http://localhost:8080/auth/login\">here</a> to log in.";

        messageHelper.setText(message, true); // Enable HTML formatting

        mailSender.send(mimeMessage);
    }

    @Override
    public void sendPasswordChange(User user) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject("Password Change Notification");
        messageHelper.setText("Your password has been changed successfully.");

        mailSender.send(mimeMessage);
    }



}
