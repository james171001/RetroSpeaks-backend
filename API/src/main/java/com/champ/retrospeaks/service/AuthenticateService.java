package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.AuthRequestDto;
import com.champ.retrospeaks.dto.AuthResponseDto;
import com.champ.retrospeaks.dto.RegisterRequest;
import org.springframework.context.annotation.Configuration;



public interface AuthenticateService {

    AuthResponseDto authenticate(AuthRequestDto authRequestDto);

    AuthResponseDto register(RegisterRequest registerRequest);
}
