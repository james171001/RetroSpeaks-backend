package com.champ.retrospeaks.model.surveyModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Surveys")
public class Survey {
    @Id
    private String id;
    private String title;
    private Long ownerId;
    private String username;
    private List<SurveyQuestions> surveyQuestions;
    private List<Long> voterList;

}
