package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.PollDto;
import com.champ.retrospeaks.model.surveyModels.Choice;
import com.champ.retrospeaks.model.surveyModels.Poll;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PollMapper {

    public static PollDto toPollDto(Optional<Poll> poll){
        PollDto pollDto = new PollDto();
        pollDto.setId(poll.get().getId());
        pollDto.setTitle(poll.get().getTitle());
        pollDto.setDescription(poll.get().getDescription());
        pollDto.setOwnerId(poll.get().getOwnerId());

        List<ChoiceDto> choiceDtos = new ArrayList<>();

        for (Choice choice : poll.get().getChoices()) {
            ChoiceDto choiceDto = new ChoiceDto();
            choiceDto.setId(choice.getId());
            choiceDto.setChoiceType(choice.getChoiceType());
            choiceDto.setChoiceTitle(choice.getChoiceTitle());
            choiceDto.setChoiceVoteCount(choice.getChoiceVoteCount());

            choiceDtos.add(choiceDto);
        }
        pollDto.setChoices(choiceDtos);
        return pollDto;
    }

}
