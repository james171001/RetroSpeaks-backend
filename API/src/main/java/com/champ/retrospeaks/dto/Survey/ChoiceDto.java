package com.champ.retrospeaks.dto.Survey;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChoiceDto {
    @Id
    @Field("_id")
    private String id;
    private String questionId;
    private String choiceTitle;
    private String choiceType;
    private int choiceVoteCount;
    private boolean chosen;
}
