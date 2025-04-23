package com.voting.votingapp.controllers;

import com.voting.votingapp.models.Poll;
import com.voting.votingapp.requests.VoteUpdateRequest;
import com.voting.votingapp.services.PollService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;

    // Dependency injection (could be autowired)
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.createPoll(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPollById(@PathVariable Long id) {
        return pollService
            .getPollById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.noContent().build());
    }

    @PatchMapping("/{id}")
    public void updateVote(
        @PathVariable Long id,
        @RequestBody VoteUpdateRequest body
    ) {
        pollService.updateVote(
            id,
            body.getOptionIndex(),
            body.getIncrementBy()
        );
    }
}
