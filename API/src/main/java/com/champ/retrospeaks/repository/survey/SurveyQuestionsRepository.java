package com.champ.retrospeaks.repository.survey;

import com.champ.retrospeaks.model.surveyModels.SurveyQuestions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyQuestionsRepository extends MongoRepository<SurveyQuestions, String> {
}
