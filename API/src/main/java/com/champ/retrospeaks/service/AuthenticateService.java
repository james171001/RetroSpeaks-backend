package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Auth.AuthRequestDto;
import com.champ.retrospeaks.dto.Auth.AuthResponseDto;
import com.champ.retrospeaks.dto.Auth.RegisterRequest;


public interface AuthenticateService {

    AuthResponseDto authenticate(AuthRequestDto authRequestDto);

    AuthResponseDto register(RegisterRequest registerRequest);


}
