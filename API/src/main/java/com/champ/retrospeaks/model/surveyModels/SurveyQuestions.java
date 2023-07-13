package com.champ.retrospeaks.model.surveyModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "SurveyQuestions")
public class SurveyQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String surveyId;
    private String questionTitle;
    private List<Choice> choices;
}
