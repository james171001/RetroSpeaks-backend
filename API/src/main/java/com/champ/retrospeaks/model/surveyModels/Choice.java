package com.champ.retrospeaks.model.surveyModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "SurveyChoices")
public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String questionId;
    private String choiceTitle;
    private String choiceType;
    private int choiceVoteCount;
    private boolean chosen;
    public boolean isChosen() {
        return true;
    }
}
