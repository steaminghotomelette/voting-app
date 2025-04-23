package com.voting.votingapp.services;

import com.voting.votingapp.models.OptionVote;
import com.voting.votingapp.models.Poll;
import com.voting.votingapp.repositories.PollRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PollService {

    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPollById(Long id) {
        return pollRepository.findById(id);
    }

    public void updateVote(Long id, int optionIndex, int incrementBy) {
        // Get the poll from db
        Poll poll = getPollById(id).orElseThrow(() ->
            new RuntimeException("Poll not found.")
        );

        // Get the list of options from the post
        List<OptionVote> options = poll.getOptions();

        // Check if we were supplied bad args (guard clause)
        if (optionIndex < 0 || optionIndex >= options.size()) {
            throw new IllegalArgumentException("Invalid option index.");
        }

        // Update the value of the option in the poll
        OptionVote selectedOption = options.get(optionIndex);
        selectedOption.setVoteCount(
            selectedOption.getVoteCount() + incrementBy
        );

        // Update the poll in the DB after mutating the object
        pollRepository.save(poll);
    }
}
