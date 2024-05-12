package com.pickkasso.domain.userRound.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRoundService {
    private UserRoundRepository userRoundRepository;
    private RoundService roundService;

    public void setUserRound(Member member, Round round){
        UserRound userRound = new UserRound(member, round);
    }
    public List<UserRoundResponse> getUserRound(Member member, Curriculum curriculum){
        List<UserRound> userRounds = userRoundRepository.findByMemberandCurriculum(member, curriculum);
        List<UserRoundResponse> userRoundResponses = new ArrayList<>();
        for(UserRound userRound : userRounds) {
            roundService.addRound(curriculum, userRound);
            UserRoundResponse userRoundResponse = new UserRoundResponse();
            userRoundResponse.setUserRoundResponse(userRound.isProgressState());
            userRoundResponses.add(userRoundResponse);
        }
        return userRoundResponses;
    }
}
