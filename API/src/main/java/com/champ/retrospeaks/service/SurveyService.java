package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.SurveyDto;
import com.champ.retrospeaks.dto.Survey.SurveyQuestionsDto;

import java.util.List;

public interface SurveyService {
    public SurveyDto saveSurvey(SurveyDto surveyDto);

    //use to vote on choices
    public SurveyDto updateSurveyChoices(String surveyId, List<ChoiceDto> updatedChoices);

    public SurveyDto updateSurvey(String surveyId, SurveyDto updatedSurveyDto);
}
