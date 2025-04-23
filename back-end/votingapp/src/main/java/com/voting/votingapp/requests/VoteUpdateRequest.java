package com.voting.votingapp.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteUpdateRequest {

    private int optionIndex;
    private int incrementBy;
}
