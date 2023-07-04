package com.champ.retrospeaks.mapper;


import com.champ.retrospeaks.dto.ErrorResponseDto;
import lombok.Builder;

import java.time.LocalDateTime;


public class AuthMapper {



    public static ErrorResponseDto exceptionToErrorResponseDto(Exception e){

        return ErrorResponseDto.builder()
                .errorMessage(e.getMessage())
                .TimeStamp(LocalDateTime.now())
                .build();
    }




}
