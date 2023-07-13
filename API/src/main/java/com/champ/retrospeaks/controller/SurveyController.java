package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.SurveyDto;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.model.surveyModels.Survey;
import com.champ.retrospeaks.service.SurveyService;
import com.champ.retrospeaks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/post/survey")
public class SurveyController {
    private final SurveyService surveyService;
    private final UserService userService;

    @Autowired
    public SurveyController(SurveyService surveyService, UserService userService) {
        this.surveyService = surveyService;
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveSurvey(@RequestBody SurveyDto surveyDto){
        surveyService.saveSurvey(surveyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public ResponseEntity<List<SurveyDto>> findAllSurvey(){
        List<SurveyDto> surveyDtos = surveyService.findAllSurvey();
        return ResponseEntity.ok(surveyDtos);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Optional<Survey>> findAllSurveyById(@PathVariable String surveyId){
        Optional<Survey> surveyDto = surveyService.findSurveyById(surveyId);
        return ResponseEntity.ok(surveyDto);
    }

    @GetMapping("/user/{ownerId}")
    public ResponseEntity<List<SurveyDto>> findAllSurveyByOwnerId(@PathVariable Long ownerId){
        List<SurveyDto> surveyDto = surveyService.findAllSurveyByOwnerId(ownerId);
        return ResponseEntity.ok(surveyDto);
    }

    @PutMapping("/{surveyId}")
    public ResponseEntity<SurveyDto> updateSurvey(@PathVariable String surveyId, @RequestBody SurveyDto updatedSurveyDto) {
        Optional<User> user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!Objects.equals(updatedSurveyDto.getOwnerId(), user.get().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        SurveyDto updatedSurvey = surveyService.updateSurvey(surveyId, updatedSurveyDto);
        if (updatedSurvey != null) {
            return ResponseEntity.ok(updatedSurvey);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{surveyId}/choices")
    public ResponseEntity<String> updateSurveyChoices(@PathVariable String surveyId, @RequestBody List<ChoiceDto> updatedChoices) {
        try {
            surveyService.updateSurveyChoices(surveyId, updatedChoices);
            return ResponseEntity.ok("Survey choices updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating survey choices");
        }
    }

}
