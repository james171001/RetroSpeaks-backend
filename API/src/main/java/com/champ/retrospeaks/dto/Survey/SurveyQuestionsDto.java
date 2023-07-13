package com.champ.retrospeaks.dto.Survey;

import com.champ.retrospeaks.model.surveyModels.Choice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyQuestionsDto {
    private String id;
    private String surveyId;
    private String questionTitle;
    private List<ChoiceDto> choices;
}
