package com.champ.retrospeaks.controller;
//
//import com.champ.retrospeaks.model.User;
//import com.champ.retrospeaks.repository.UserRepository;
//import com.champ.retrospeaks.service.ForgotPasswordService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Optional;
//
//
//@Controller("/api/auth")
//public class ForgotPasswordController {
//

//    private final ForgotPasswordService forgotPasswordService;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public ForgotPasswordController(ForgotPasswordService forgotPasswordService, UserRepository userRepository) {
//        this.forgotPasswordService = forgotPasswordService;
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/forgot-password")
//    public String showForgotPasswordForm() {
//        return "forgot-password";
//    }
//
//    @PostMapping("/forgot-password")
//    public String sendResetPasswordEmail(@RequestParam("email") String email, Model model) {
//        // Check if the email exists in the system
//        Optional<User> optionalUser = userRepository.findByEmail(email);
//        if (optionalUser.isPresent()) {
//            forgotPasswordService.sendPasswordResetEmail(email);
//            model.addAttribute("emailSent", true);
//        } else {
//            model.addAttribute("emailSent", false);
//        }
//        return "";
//    }
//
//    @GetMapping("/reset-password")
//    public String showResetPasswordForm(@RequestParam("token") String resetToken, Model model) {
//        if (forgotPasswordService.isValidResetToken(resetToken)) {
//            model.addAttribute("resetToken", resetToken);
//            return "change-password";
//        } else {
//            // Invalid reset token
//            return "error"; // Replace with desired error page
//        }
//    }
//
//    @PostMapping("/reset-password")
//    public String resetPassword(@RequestParam("token") String resetToken,
//                                @RequestParam("newPassword") String newPassword,
//                                @RequestParam("confirmPassword") String confirmPassword,
//                                Model model) {
//        if (!newPassword.equals(confirmPassword)) {
//            // Passwords do not match
//            model.addAttribute("resetToken", resetToken);
//            model.addAttribute("errorMessage", "Passwords do not match");
//            return "change-password";
//        }
//
//        if (forgotPasswordService.isValidResetToken(resetToken)) {
//            forgotPasswordService.resetPassword(resetToken, newPassword);
//            return "redirect:/login";
//        } else {
//            // Invalid reset token
//            return "error"; // Replace with your desired error page
//        }
//    }
//}


import com.champ.retrospeaks.model.ForgotPassword;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.ForgotPasswordService;
import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class ForgotPasswordController {

    private final ForgotPasswordService forgotPasswordService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public ForgotPasswordController(ForgotPasswordService forgotPasswordService, UserRepository userRepository, UserService userService) {
        this.forgotPasswordService = forgotPasswordService;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PostMapping("/forgot-password")
    public String sendResetPasswordEmail(@RequestBody ForgotPassword forgotPassword, Model model) {
        String email = forgotPassword.getEmail();
        // Check if the email exists in the system
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty()){
            return "no user found";
        }
        if (optionalUser.isPresent()) {
            forgotPasswordService.sendPasswordResetEmail(email);
            model.addAttribute("emailSent", true);
        } else {
            model.addAttribute("emailSent", false);
            return "password reset fail";
        }
        return "reset-password-email-sent";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam("token") String resetToken, Model model) {
        if (forgotPasswordService.isValidResetToken(resetToken)) {
            model.addAttribute("resetToken", resetToken);
            return "change-password";
        } else {
            // Invalid reset token
            return "error"; // Replace with desired error page
        }
    }
}

