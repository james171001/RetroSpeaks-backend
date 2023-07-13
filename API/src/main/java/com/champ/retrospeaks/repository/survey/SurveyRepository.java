package com.champ.retrospeaks.repository.survey;

import com.champ.retrospeaks.model.surveyModels.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SurveyRepository extends MongoRepository<Survey, String> {
    Optional<Survey> findSurveyByOwnerId(String ownerId);
}
