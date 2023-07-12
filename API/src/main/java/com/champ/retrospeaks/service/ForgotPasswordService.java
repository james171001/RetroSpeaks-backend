package com.champ.retrospeaks.service;

//public interface ForgotPasswordService {
//    void sendPasswordResetEmail(String email);
//    String generateResetToken();
//    String generateResetLink(String resetToken);
//    void resetPassword( String resetToken, String newPassword);
//    boolean isValidResetToken(String resetToken);
//}

import com.champ.retrospeaks.model.User;

public interface ForgotPasswordService {
    void sendPasswordResetEmail(String email);
    boolean isValidResetToken(String resetToken);
}

