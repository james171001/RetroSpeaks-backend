package com.champ.retrospeaks.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class EmailMessage {
    private Long userId;
    private String to;
    private String subject;
    private String message;
}
