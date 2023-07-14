package com.champ.retrospeaks.controller;

import com.champ.retrospeaks.dto.Survey.ChoiceDto;
import com.champ.retrospeaks.dto.Survey.PollDto;
import com.champ.retrospeaks.model.User;
import com.champ.retrospeaks.service.PollService;
import com.champ.retrospeaks.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/post/poll")
public class PollController {


    private final PollService pollService;
    private final UserService userService;

    public PollController(PollService pollService, UserService userService) {
        this.pollService = pollService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> savePoll(@RequestBody PollDto pollDto){
        pollService.savePoll(pollDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<PollDto>> findAllPoll(@RequestBody PollDto pollDto){
        List<PollDto> polls = pollService.findAllPolls();
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<PollDto> findAllSurveyById(@PathVariable String pollId){
        PollDto Poll = pollService.findPollById(pollId);
        return ResponseEntity.ok(Poll);
    }

    @GetMapping("/user/{ownerId}")
    public ResponseEntity<List<PollDto>> findAllSurveyByOwnerId(@PathVariable Long ownerId){
        List<PollDto> pollDtos = pollService.findAllPollByOwnerId(ownerId);
        return ResponseEntity.ok(pollDtos);
    }

    @PutMapping("/{pollId}/choices")
    public ResponseEntity<String> updateSurveyChoices(@PathVariable String pollId, @RequestBody List<ChoiceDto> updatedChoices) {
        try {
            pollService.updatePollChoices(pollId, updatedChoices);
            return ResponseEntity.ok("Poll choices updated successfully, vote submitted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating Poll choices");
        }
    }

    @PutMapping("/{pollId}")
    public ResponseEntity<PollDto> updateSurvey(@PathVariable String pollId, @RequestBody PollDto updatedPollDto) {
        Optional<User> user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(!Objects.equals(updatedPollDto.getOwnerId(), user.get().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        PollDto  updatedPoll= pollService.updatePoll(pollId, updatedPollDto);
        if (updatedPoll != null) {
            return ResponseEntity.ok(updatedPoll);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
