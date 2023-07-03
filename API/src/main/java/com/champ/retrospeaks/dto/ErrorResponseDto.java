package com.champ.retrospeaks.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor
@Builder
public class ErrorResponseDto {

    private String errorMessage;
    private LocalDateTime TimeStamp;
}
