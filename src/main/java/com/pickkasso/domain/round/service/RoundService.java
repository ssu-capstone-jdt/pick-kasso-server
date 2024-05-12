package com.pickkasso.domain.round.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.domain.UserRound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class RoundService {
    private RoundRepository roundRepository;

    public List<RoundResponse> getRound(Curriculum curriculum){
        List<Round> rounds = roundRepository.findByCurriculum(curriculum);
        List<RoundResponse> roundResponses = rounds.stream()
                .map(round -> new RoundResponse(round.getOrder(), round.getTime(), round.getExplanation()))
                .collect(Collectors.toList());

        return roundResponses;
    }

    public void addRound(Curriculum curriculum, UserRound userRound){
        Round round = userRound.getRound();
        round.getUserRounds().add(userRound);
    }

    public void setRound(Curriculum curriculum, int order, char time, String explanation){
        Round round = Round.builder()
                .curriculum(curriculum)
                .order(order)
                .time(time)
                .explanation(explanation)
                .build();
    }
}
