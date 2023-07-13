package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.config.security.JwtService;
import com.champ.retrospeaks.dto.Auth.AuthRequestDto;
import com.champ.retrospeaks.dto.Auth.AuthResponseDto;
import com.champ.retrospeaks.dto.Auth.RegisterRequest;
import com.champ.retrospeaks.mapper.AuthMapper;
import com.champ.retrospeaks.mapper.UserMapper;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticateServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getUserName(),
                            authRequestDto.getPassWord()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        User user = userRepository.findByUserName(authRequestDto.getUserName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        String jwtToken = jwtService.generateToken(map,user);
        return AuthMapper.mapToDto(jwtToken, "Successful  Login");
    }

    @Override
    public AuthResponseDto register(RegisterRequest registerRequest) {

        User user = UserMapper.registerRequestToUser(registerRequest,passwordEncoder);
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);

        return AuthMapper.mapToDto(jwtToken, "Successful Registration");

    }
}
