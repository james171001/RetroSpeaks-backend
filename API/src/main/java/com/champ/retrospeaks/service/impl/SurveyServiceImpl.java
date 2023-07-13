package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.SurveyDto;
import com.champ.retrospeaks.dto.Survey.SurveyQuestionsDto;
import com.champ.retrospeaks.mapper.SurveyMapper;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.model.surveyModels.Choice;
import com.champ.retrospeaks.model.surveyModels.Survey;
import com.champ.retrospeaks.model.surveyModels.SurveyQuestions;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.repository.survey.ChoiceRepository;
import com.champ.retrospeaks.repository.survey.SurveyQuestionsRepository;
import com.champ.retrospeaks.repository.survey.SurveyRepository;
import com.champ.retrospeaks.service.SurveyService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.champ.retrospeaks.mapper.SurveyMapper.toSurveyDto;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final SurveyQuestionsRepository surveyQuestionsRepository;
    private final ChoiceRepository choiceRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository, UserRepository userRepository, SurveyQuestionsRepository surveyQuestionsRepository, ChoiceRepository choiceRepository) {
        this.surveyRepository = surveyRepository;
        this.userRepository = userRepository;
        this.surveyQuestionsRepository = surveyQuestionsRepository;
        this.choiceRepository = choiceRepository;
    }

    @Override
    public List<SurveyDto> findAllSurvey() {
        List<Survey> surveys = surveyRepository.findAll();
            return surveys.stream().map(SurveyMapper::toSurveyDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Survey> findSurveyById(String surveyId) {
        return surveyRepository.findById(surveyId);
    }

    @Override
    public List<SurveyDto> findAllSurveyByOwnerId(Long ownerId) {
        Optional<Survey> surveys = surveyRepository.findSurveyByOwnerId(ownerId);
        return surveys.stream().map(SurveyMapper::toSurveyDto).collect(Collectors.toList());
    }

    @Override
    public SurveyDto saveSurvey(SurveyDto surveyDto) {
        Survey survey = new Survey();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> surveyOwner = userRepository.findByUserName(username);

        survey.setOwnerId(surveyOwner.get().getId());
        survey.setUsername(username);
        survey.setTitle(surveyDto.getTitle());

        List<SurveyQuestionsDto> surveyQuestionDtos = surveyDto.getSurveyQuestions();
        List<SurveyQuestions> surveyQuestions = new ArrayList<>();

        for (SurveyQuestionsDto surveyQuestionDto : surveyQuestionDtos) {
            SurveyQuestions surveyQuestion = new SurveyQuestions();
            surveyQuestion.setSurveyId(surveyDto.getId());
            surveyQuestion.setQuestionTitle(surveyQuestionDto.getQuestionTitle());

            // Map and save each choice for the survey question
            List<ChoiceDto> choiceDtos = surveyQuestionDto.getChoices();
            List<Choice> choices = new ArrayList<>();

            for (ChoiceDto choiceDto : choiceDtos) {
                Choice choice = new Choice();
                choice.setQuestionId(surveyQuestionDto.getId());
                choice.setChoiceTitle(choiceDto.getChoiceTitle());
                choice.setChoiceType(choiceDto.getChoiceType());
                choice.setChosen(false);
                choice.setChoiceVoteCount(choiceDto.getChoiceVoteCount());
                choices.add(choice);
                choiceRepository.save(choice);
            }
            surveyQuestion.setChoices(choices);
            surveyQuestions.add(surveyQuestion);
            surveyQuestionsRepository.save(surveyQuestion);

        }
        survey.setSurveyQuestions(surveyQuestions);


        Survey savedSurvey = surveyRepository.save(survey);
        return toSurveyDto(savedSurvey);
    }

    @Override
    public SurveyDto updateSurveyChoices(String surveyId, List<ChoiceDto> updatedChoices) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUserName(username);
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);

        if (surveyOptional.isPresent()) {
            Survey survey = surveyOptional.get();
            List<SurveyQuestions> surveyQuestions = survey.getSurveyQuestions();


            for (SurveyQuestions surveyQuestion : surveyQuestions) {
                List<Choice> choices = surveyQuestion.getChoices();
                for (ChoiceDto updatedChoice : updatedChoices) {
                    for (Choice choice : choices) {
                        if (choice.getId().equals(updatedChoice.getId())) {
                            choice.setChosen(updatedChoice.isChosen());
                            if (updatedChoice.isChosen()) {
                                choice.setChoiceVoteCount(choice.getChoiceVoteCount() + 1);
                            }
                            choiceRepository.save(choice);
                            break;
                        }
                    }
                }
            }
            //setting voter-list
            //no voters yet
            if(surveyOptional.get().getVoterList() == null){
                List<Long> voterlist = new ArrayList<>();
                voterlist.add(user.get().getId());
                survey.setVoterList(voterlist);
            }
            //there are existing voters
            else{
                List<Long> voterlist = surveyOptional.get().getVoterList();
                if(voterlist.contains(user.get().getId())){
                    throw new IllegalArgumentException("You already voted");
                }
                voterlist.add(user.get().getId());
                survey.setVoterList(voterlist);
            }

            surveyRepository.save(survey); // Save the updated survey object
            return toSurveyDto(survey);
        }
        return null;
    }


    @Override
    public SurveyDto updateSurvey(String surveyId, SurveyDto updatedSurveyDto) {
        Optional<Survey> surveyOptional = surveyRepository.findById(surveyId);

        if (surveyOptional.isPresent()) {
            Survey existingSurvey = surveyOptional.get();

            // Update the survey details
            existingSurvey.setTitle(updatedSurveyDto.getTitle());
            existingSurvey.setVoterList(existingSurvey.getVoterList());

            // Update the survey questions and choices
            List<SurveyQuestionsDto> updatedQuestions = updatedSurveyDto.getSurveyQuestions();
            List<SurveyQuestions> existingQuestions = existingSurvey.getSurveyQuestions();

            for (int i = 0; i < updatedQuestions.size(); i++) {
                SurveyQuestionsDto updatedQuestion = updatedQuestions.get(i);
                SurveyQuestions existingQuestion = existingQuestions.get(i);

                // Update the question title
                existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());

                // Update the choices
                List<ChoiceDto> updatedChoices = updatedQuestion.getChoices();
                List<Choice> existingChoices = existingQuestion.getChoices();

                for (int j = 0; j < updatedChoices.size(); j++) {
                    ChoiceDto updatedChoice = updatedChoices.get(j);
                    Choice existingChoice = existingChoices.get(j);

                    // Update the choice details
                    existingChoice.setChoiceTitle(updatedChoice.getChoiceTitle());
                }
            }

            surveyRepository.save(existingSurvey); // Save the updated survey object

            return toSurveyDto(existingSurvey);
        }

        return null; // or throw an exception indicating survey not found
    }

}
