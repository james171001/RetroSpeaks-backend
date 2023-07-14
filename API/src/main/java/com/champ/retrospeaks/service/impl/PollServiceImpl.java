package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.PollDto;
import com.champ.retrospeaks.mapper.PollMapper;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.model.surveyModels.Choice;
import com.champ.retrospeaks.model.surveyModels.Poll;
import com.champ.retrospeaks.model.surveyModels.Survey;
import com.champ.retrospeaks.repository.UserRepository;
import com.champ.retrospeaks.repository.survey.ChoiceRepository;
import com.champ.retrospeaks.repository.survey.PollRepository;
import com.champ.retrospeaks.service.PollService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.champ.retrospeaks.mapper.PollMapper.toPollDto;

@Service
public class PollServiceImpl implements PollService {

    private final UserRepository userRepository;
    private final ChoiceRepository choiceRepository;
    private final PollRepository pollRepository;


    public PollServiceImpl(UserRepository userRepository, ChoiceRepository choiceRepository, PollRepository pollRepository) {
        this.userRepository = userRepository;
        this.choiceRepository = choiceRepository;
        this.pollRepository = pollRepository;
    }

    @Override
    public PollDto savePoll(PollDto pollDto) {
        Poll newPoll = new Poll();
        Optional<User> pollOwner = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        newPoll.setOwnerId(pollOwner.get().getId());
        newPoll.setUsername(pollOwner.get().getUsername());
        newPoll.setTitle(pollDto.getTitle());
        newPoll.setDescription(pollDto.getDescription());

        List<ChoiceDto> choiceDtos = pollDto.getChoices();
        List<Choice> choices = new ArrayList<>();
        for (ChoiceDto choiceDto : choiceDtos) {
            Choice choice = new Choice();
            choice.setQuestionId(pollDto.getId());
            choice.setChoiceTitle(choiceDto.getChoiceTitle());
            choice.setChoiceType(choiceDto.getChoiceType());
            choice.setChosen(false);
            choice.setChoiceVoteCount(choiceDto.getChoiceVoteCount());
            choices.add(choice);
            choiceRepository.save(choice);
        }
        newPoll.setChoices(choices);
        newPoll.setVoterList(pollDto.getVoterList());
        pollRepository.save(newPoll);

        return toPollDto(Optional.of(newPoll));
    }

    @Override
    public List<PollDto> findAllPolls() {
        List<Poll> polls = pollRepository.findAll();
        return polls.stream().map(poll -> toPollDto(Optional.ofNullable(poll))).collect(Collectors.toList());
    }

    @Override
    public PollDto findPollById(String id) {
        Optional<Poll> poll = pollRepository.findById(id);
        return toPollDto(poll);
    }

    @Override
    public List<PollDto> findAllPollByOwnerId(Long ownerId) {
        List<Optional<Poll>> polls = pollRepository.findPollByOwnerId(ownerId);
        return polls.stream().map(PollMapper::toPollDto).collect(Collectors.toList());
    }

    @Override
    public PollDto updatePollChoices(String pollId, List<ChoiceDto> updatedChoices) {
        Optional<User> user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        Optional<Poll> existingPoll = pollRepository.findById(pollId);

        if(existingPoll.isPresent()){
            Poll poll = existingPoll.get();
            List<Choice> choices = poll.getChoices();
            for (ChoiceDto updatedChoice : updatedChoices) {
                for (Choice choice : choices) {
                    if (choice.getId().equals(updatedChoice.getId())) {
                        choice.setChosen(updatedChoice.isChosen());
                        if (updatedChoice.isChosen()) {
                            choice.setChoiceVoteCount(choice.getChoiceVoteCount() + 1);
                            choice.setChosen(false);
                        }
                        choiceRepository.save(choice);
                        break;
                    }
                }
            }
            //setting voter-list
            //no voters yet
            if(existingPoll.get().getVoterList() == null){
                List<Long> voterlist = new ArrayList<>();
                voterlist.add(user.get().getId());
                poll.setVoterList(voterlist);
            }
            //there are existing voters
            else{
                List<Long> voterlist = existingPoll.get().getVoterList();
                if(voterlist.contains(user.get().getId())){
                    throw new IllegalArgumentException("You already voted");
                }
                voterlist.add(user.get().getId());
                poll.setVoterList(voterlist);
            }

            pollRepository.save(poll);
            return toPollDto(Optional.of(poll));
        }
        return null;
    }

    @Override
    public PollDto updatePoll(String pollId, PollDto pollDto) {
        Optional<Poll> exisitingPoll = pollRepository.findById(pollId);

        if(exisitingPoll.isPresent()){
            Poll poll = exisitingPoll.get();

            poll.setTitle(pollDto.getTitle());
            poll.setVoterList(exisitingPoll.get().getVoterList());

            List<ChoiceDto> updatedChoices = pollDto.getChoices();
            List<Choice> existingChoices = exisitingPoll.get().getChoices();

            for (int j = 0; j < updatedChoices.size(); j++) {
                ChoiceDto updatedChoice = updatedChoices.get(j);
                Choice existingChoice = existingChoices.get(j);

                // Update the choice details
                existingChoice.setChoiceTitle(updatedChoice.getChoiceTitle());
            }
            pollRepository.save(poll);
            return toPollDto(Optional.of(poll));
        }
        return null;
    }
}
