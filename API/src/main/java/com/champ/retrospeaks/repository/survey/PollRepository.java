package com.champ.retrospeaks.repository.survey;

import com.champ.retrospeaks.model.surveyModels.Poll;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

public interface PollRepository extends MongoRepository<Poll, String> {

    List<Optional<Poll>>findPollByOwnerId(Long ownerId);

}
