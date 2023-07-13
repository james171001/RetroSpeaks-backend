package com.champ.retrospeaks.repository.survey;

import com.champ.retrospeaks.model.surveyModels.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey, String> {
}
