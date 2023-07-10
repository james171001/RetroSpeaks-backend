package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.EmailSenderService;
import com.champ.retrospeaks.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private final UserRepository userRepository;
    private final EmailSenderService emailSenderService;

    @Autowired
    public ForgotPasswordServiceImpl(UserRepository userRepository, EmailSenderService emailSenderService) {
        this.userRepository = userRepository;
        this.emailSenderService = emailSenderService;
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String resetToken = generateResetToken();

            // Save the reset token to the user
            user.setResetToken(resetToken);
            userRepository.save(user);

            String resetLink = generateResetLink(resetToken);

            // Send the reset link to the user's email
            String emailContent = "Click the link below to reset your password:\n" + resetLink;
            emailSenderService.sendEmail(email, "Password Reset", emailContent);
        }
    }

    @Override
    public String generateResetToken() {
        // Generate a random alphanumeric string as the reset token
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int tokenLength = 20; // Length of the reset token
        StringBuilder tokenBuilder = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < tokenLength; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            tokenBuilder.append(randomChar);
        }

        return tokenBuilder.toString();
    }

    @Override
    public String generateResetLink(String resetToken) {
        String resetLink = "http://localhost:8080/forgot-password?token=" + resetToken;
        return resetLink;
    }

    @Override
    public void resetPassword(String resetToken, String newPassword) {
        Optional<User> optionalUser = userRepository.findByResetToken(resetToken);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassWord(newPassword);
            user.setResetToken(null); // Reset the token after successful password change
            userRepository.save(user);
        }
    }

    @Override
    public boolean isValidResetToken(String resetToken) {
        // Check if the reset token exists in the database
        Optional<User> optionalUser = userRepository.findByResetToken(resetToken);
        return optionalUser.isPresent();
    }
}
