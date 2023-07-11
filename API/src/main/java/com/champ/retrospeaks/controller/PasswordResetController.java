package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.model.ResetToken;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.ResetTokenRepository;
import com.champ.retrospeaks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
public class PasswordResetController {

    private final ResetTokenRepository tokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordResetController(ResetTokenRepository tokenRepository, UserRepository userRepository,
                                   PasswordEncoder passwordEncoder) {
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
                                                @RequestParam("newPassword") String newPassword) {
        Optional<ResetToken> optionalToken = tokenRepository.findByToken(token);
        if (optionalToken.isPresent()) {
            ResetToken resetToken = optionalToken.get();
            if (isTokenExpired(resetToken)) {
                // Token has expired
                return ResponseEntity.badRequest().body("Password reset token has expired");
            }

            User user = resetToken.getUser();
            // Update the user's password
            user.setPassWord(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Delete the used token
            tokenRepository.delete(resetToken);

            return ResponseEntity.ok("Password reset successfully");
        }

        return ResponseEntity.badRequest().body("Invalid password reset token");
    }

    private boolean isTokenExpired(ResetToken resetToken) {
        LocalDateTime expirationTime = resetToken.getExpirationTime();
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(expirationTime);
    }
}



