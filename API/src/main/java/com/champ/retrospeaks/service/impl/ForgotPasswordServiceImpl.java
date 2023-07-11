package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.model.ResetToken;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.ResetTokenRepository;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.EmailSenderService;
import com.champ.retrospeaks.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
//
//@Service
//public class ForgotPasswordServiceImpl implements ForgotPasswordService {
//
//    private final UserRepository userRepository;
//    private final EmailSenderService emailSenderService;
//
//    @Autowired
//    public ForgotPasswordServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService) {
//        this.userRepository = userRepository;
//        this.emailSenderService = emailSenderService;
//    }
//
//    @Override
//    public void sendPasswordResetEmail(String email) {
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String resetToken = generateResetToken();
//
//            // Save the reset token to the user
//            user.setResetToken(resetToken);
//            userRepository.save(user);
//
//            String resetLink = generateResetLink(resetToken);
//
//            // Send the reset link to the user's email
//            String emailContent = "Click the link below to reset your password:\n" + resetLink;
//            emailSenderService.sendEmail(email, "Password Reset", emailContent);
//        }
//    }
//
//    @Override
//    public String generateResetToken() {
//        // Generate a random alphanumeric string as the reset token
//        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
//        int tokenLength = 20; // Length of the reset token
//        StringBuilder tokenBuilder = new StringBuilder();
//
//        Random random = new Random();
//        for (int i = 0; i < tokenLength; i++) {
//            int index = random.nextInt(characters.length());
//            char randomChar = characters.charAt(index);
//            tokenBuilder.append(randomChar);
//        }
//
//        return tokenBuilder.toString();
//    }
//
//    @Override
//    public String generateResetLink(String resetToken) {
//        String resetLink = "http://localhost:8080/forgot-password?token=" + resetToken;
//        return resetLink;
//    }
//
//    @Override
//    public void resetPassword(String resetToken, String newPassword) {
//        Optional<User> optionalUser = userRepository.findByResetToken(resetToken);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setPassWord(newPassword);
//            user.setResetToken(null); // Reset the token after successful password change
//            userRepository.save(user);
//        }
//    }
//
//    @Override
//    public boolean isValidResetToken(String resetToken) {
//        // Check if the reset token exists in the database
//        Optional<User> optionalUser = userRepository.findByResetToken(resetToken);
//        return optionalUser.isPresent();
//    }
//}

import java.util.UUID;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserRepository userRepository;
    private final ResetTokenRepository tokenRepository;
    private final JavaMailSender javaMailSender;
    private final EmailSenderService emailSenderService;

    @Autowired
    public ForgotPasswordServiceImpl(UserRepository userRepository, ResetTokenRepository tokenRepository,
                                     JavaMailSender javaMailSender, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.javaMailSender = javaMailSender;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Generate a unique reset token
            String resetToken = UUID.randomUUID().toString();

            // Set the expiration time (e.g., 1 hour from now)
            LocalDateTime expirationTime = LocalDateTime.now().plusHours(1);

            // Create and save the token in the database
            ResetToken token = new ResetToken();
            token.setToken(resetToken);
            token.setUser(user);
            token.setExpirationTime(expirationTime);
            tokenRepository.save(token);

            // Send the password reset email
        emailSenderService.sendEmail(email, "Password Reset", resetToken);
        }
    }

    private void sendEmail(String recipientEmail, String resetToken) {
        // Create the reset password link with the reset token
        String resetLink = "http://localhost:8080/reset-password?token=" + resetToken;

        // Compose the email content
        String emailContent = "Please click the following link to reset your password: " + resetLink;

        // Send the email
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Password Reset");
        mailMessage.setText(emailContent);
        javaMailSender.send(mailMessage);
    }
}



