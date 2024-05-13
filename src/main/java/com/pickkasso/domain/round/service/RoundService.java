package com.pickkasso.domain.round.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.domain.UserRound;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public List<RoundResponse> getRound(Curriculum curriculum) {
        List<Round> rounds = roundRepository.findByCurriculum(curriculum);
        List<RoundResponse> roundResponses =
                rounds.stream()
                        .map(
                                round ->
                                        new RoundResponse(
                                                round.getRoundSeq(),
                                                round.getTime(),
                                                round.getExplanation()))
                        .collect(Collectors.toList());

        return roundResponses;
    }

    public void addRound(Curriculum curriculum, UserRound userRound) {
        Round round = userRound.getRound();
        round.getUserRounds().add(userRound);
    }

    public Round setRound(Curriculum curriculum, int order, String time, String explanation) {
        Round round =
                Round.builder()
                        .curriculum(curriculum)
                        .order(order)
                        .time(time)
                        .explanation(explanation)
                        .build();
        return roundRepository.save(round);
    }
}
