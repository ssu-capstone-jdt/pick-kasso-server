package com.pickkasso.domain.userRound.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRoundService {
    private final UserRoundRepository userRoundRepository;
    private final RoundService roundService;

    public void setUserRound(Member member, Round round) {
        UserRound userRound = new UserRound(member, round);
    }

    public List<UserRoundResponse> getUserRound(Member member, Curriculum curriculum) {
        List<UserRoundResponse> userRoundResponses = new ArrayList<>();
        for (Round round : curriculum.getRounds()) {
            List<UserRound> userRounds = userRoundRepository.findByMemberAndRound(member, round);
            for (UserRound userRound : userRounds) {
                UserRoundResponse userRoundResponse = new UserRoundResponse();
                userRoundResponse.setUserRoundResponse(userRound.isProgressState());
                userRoundResponses.add(userRoundResponse);
            }
        }
        return userRoundResponses;
    }

    public void changeUserRoundState(long id) {
        UserRound userRound = userRoundRepository.findById(id).orElseThrow();
        userRound.changeState();
    }
}
