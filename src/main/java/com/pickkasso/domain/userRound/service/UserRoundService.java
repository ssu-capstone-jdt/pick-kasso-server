package com.pickkasso.domain.userRound.service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserRoundService {
    private UserRoundRepository userRoundRepository;
    private RoundService roundService;

    public void setUserRound(Member member, Round round){
        UserRound userRound = new UserRound(member, round);
    }
    public UserRoundResponse getUserRound(Member member, Curriculum curriculum){
        UserRound userRound = userRoundRepository.findByMemberandCurriculum(member, curriculum);
        roundService.addRound(curriculum, userRound);
        UserRoundResponse userRoundResponse = new UserRoundResponse();
        userRoundResponse.setUserRoundResponse(userRound.isProgressState());
        return userRoundResponse;
    }
}
