package com.pickkasso.domain.round.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.userRound.domain.UserRound;
import org.springframework.stereotype.Service;

@Service

public class RoundService {
    private RoundRepository roundRepository;

    public RoundResponse getRound(Curriculum curriculum){
        Round round = roundRepository.findByCurriculum(curriculum);
        RoundResponse roundResponse = new RoundResponse(round.getOrder(), round.getTime(), round.getExplanation());

        return roundResponse;
    }

    public void addRound(Curriculum curriculum, UserRound userRound){
        Round round = roundRepository.findByCurriculum(curriculum);
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
