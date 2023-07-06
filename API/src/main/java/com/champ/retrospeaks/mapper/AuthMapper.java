package com.champ.retrospeaks.mapper;


import com.champ.retrospeaks.dto.Auth.AuthResponseDto;
import com.champ.retrospeaks.dto.ErrorResponseDto;


import java.time.LocalDateTime;


public class AuthMapper {

    //Don't allow to create an instance for utility class
    private AuthMapper(){
        throw new IllegalStateException("Utility Class");
    }



    public static ErrorResponseDto exceptionToErrorResponseDto(Exception e){

        return ErrorResponseDto.builder()
                .errorMessage(e.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
    }
    public static AuthResponseDto mapToDto(String jwtToken, String message) {
        return AuthResponseDto.builder()
                .token(jwtToken)
                .message(message)
                .build();
    }







}
