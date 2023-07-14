package com.champ.retrospeaks.dto.Survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PollDto {
    private String id;
    private String title;
    private String description;
    private Long ownerId;
    private String username;
    private List<ChoiceDto> choices;
    private List<Long> voterList;
}
