package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.PollDto;
import com.champ.retrospeaks.model.surveyModels.Poll;

import java.util.List;

public interface PollService {

    public PollDto savePoll(PollDto pollDto);
    public List<PollDto> findAllPolls();

    public PollDto findPollById(String id);

    List<PollDto> findAllPollByOwnerId(Long ownerId);

    public PollDto updatePollChoices(String pollId, List<ChoiceDto> updatedChoices);

    public PollDto updatePoll(String pollId, PollDto pollDto);



}
