package com.champ.retrospeaks.repository.survey;

import com.champ.retrospeaks.model.surveyModels.Choice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChoiceRepository extends MongoRepository<Choice, String> {
}
