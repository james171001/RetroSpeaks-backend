package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.AuthRequestDto;
import com.champ.retrospeaks.dto.AuthResponseDto;
import com.champ.retrospeaks.dto.ErrorResponseDto;
import com.champ.retrospeaks.dto.RegisterRequest;
import com.champ.retrospeaks.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthenticateService authService;


    @Autowired
    public AuthController(AuthenticateService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseDto.builder()
                        .errorMessage(exception.getMessage())
                        .TimeStamp(LocalDateTime.now())
                        .build());

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseDto.builder()
                        .errorMessage(exception.getMessage())
                        .TimeStamp(LocalDateTime.now())
                        .build());



    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFoundException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponseDto.builder()
                        .errorMessage(exception.getMessage())
                        .TimeStamp(LocalDateTime.now())
                        .build());

    }



}
