package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.SurveyDto;
import com.champ.retrospeaks.dto.Survey.SurveyQuestionsDto;
import com.champ.retrospeaks.model.surveyModels.Choice;
import com.champ.retrospeaks.model.surveyModels.Survey;
import com.champ.retrospeaks.model.surveyModels.SurveyQuestions;

import java.util.ArrayList;
import java.util.List;

public class SurveyMapper {

    public static SurveyDto toSurveyDto(Survey survey){
        SurveyDto surveyDto = new SurveyDto();
        surveyDto.setId(survey.getId());
        surveyDto.setTitle(survey.getTitle());
        surveyDto.setOwnerId(surveyDto.getOwnerId());
        surveyDto.setVoterList(survey.getVoterList());
        surveyDto.setUsername(survey.getUsername());

        List<SurveyQuestionsDto> surveyQuestionDtos = new ArrayList<>();
        for (SurveyQuestions surveyQuestion : survey.getSurveyQuestions()) {
            SurveyQuestionsDto surveyQuestionDto = new SurveyQuestionsDto();
            surveyQuestionDto.setId(surveyQuestion.getId());
            surveyQuestionDto.setQuestionTitle(surveyQuestion.getQuestionTitle());

            List<ChoiceDto> choiceDtos = new ArrayList<>();

            for (Choice choice : surveyQuestion.getChoices()) {
                ChoiceDto choiceDto = new ChoiceDto();
                choiceDto.setId(choice.getId());
                choiceDto.setChoiceTitle(choice.getChoiceTitle());
                choiceDto.setChoiceType(choice.getChoiceType());
                choiceDto.setChoiceVoteCount(choice.getChoiceVoteCount());

                choiceDtos.add(choiceDto);
            }

            surveyQuestionDto.setChoices(choiceDtos);
            surveyQuestionDtos.add(surveyQuestionDto);
        }
        surveyDto.setSurveyQuestions(surveyQuestionDtos);
        return surveyDto;
    }
}
