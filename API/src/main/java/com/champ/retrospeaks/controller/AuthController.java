package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Auth.AuthRequestDto;
import com.champ.retrospeaks.dto.Auth.AuthResponseDto;
import com.champ.retrospeaks.dto.ErrorResponseDto;
import com.champ.retrospeaks.dto.Auth.RegisterRequest;
import com.champ.retrospeaks.mapper.AuthMapper;
import com.champ.retrospeaks.service.AuthenticateService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticateService authService;


    @Autowired
    public AuthController( AuthenticateService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto loginRequest) {
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verifyAccount(token);
    };

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthMapper.exceptionToErrorResponseDto(exception));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthMapper.exceptionToErrorResponseDto(exception));


    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFoundException(UsernameNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthMapper.exceptionToErrorResponseDto(exception));

    }
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponseDto> handleExpiredJwtException(ExpiredJwtException exception) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(AuthMapper.exceptionToErrorResponseDto(exception));

    }

}
