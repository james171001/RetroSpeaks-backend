package com.champ.retrospeaks.dto.Survey;

import com.champ.retrospeaks.model.surveyModels.SurveyQuestions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyDto {
    private String id;
    private String title;
    private Long ownerId;
    private String username;
    private List<SurveyQuestionsDto> surveyQuestions;
    private List<Long> voterList;
}
