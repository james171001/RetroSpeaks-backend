package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.SurveyDto;
import com.champ.retrospeaks.model.surveyModels.Survey;

import java.util.List;
import java.util.Optional;

public interface SurveyService {
    public List<SurveyDto> findAllSurvey();
    public Optional<Survey> findSurveyById(String surveyId);

    List<SurveyDto> findAllSurveyByOwnerId(Long ownerId);

    public SurveyDto saveSurvey(SurveyDto surveyDto);

    //use to vote on choices
    public SurveyDto updateSurveyChoices(String surveyId, List<ChoiceDto> updatedChoices);

    public SurveyDto updateSurvey(String surveyId, SurveyDto updatedSurveyDto);
}
