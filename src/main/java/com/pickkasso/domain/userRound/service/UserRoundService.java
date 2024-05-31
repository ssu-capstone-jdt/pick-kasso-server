package com.pickkasso.domain.userRound.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.UserRoundUploadStatus;
import com.pickkasso.domain.userRound.dao.UserRoundRepository;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.dto.UserRoundCompleteResponse;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserRoundService {
    private final UserRoundRepository userRoundRepository;
    private final RoundRepository roundRepository;

    public UserRound setUserRound(Member member, Round round) {
        UserRound userRound = UserRound.createUserRound(member, round);
        return userRoundRepository.save(userRound);
    }


    public List<UserRoundResponse> getUserRound(Member member, Curriculum curriculum) {
        List<UserRoundResponse> userRoundResponses = new ArrayList<>();
        for (Round round : curriculum.getRounds()) {
            UserRound userRound = userRoundRepository.findByMemberAndRound(member, round);
            UserRoundResponse userRoundResponse = new UserRoundResponse();
            userRoundResponse.setUserRoundResponse(userRound.isProgressState());
            userRoundResponses.add(userRoundResponse);
        }
        return userRoundResponses;
    }

    public boolean isDownload(Member member, Curriculum curriculum) {
        for (Round round : curriculum.getRounds()) {
            if (!userRoundRepository.existsByMemberAndRound(member, round)) {
                return false;
            }
        }
        return true;
    }

    public void changeUserRoundState(Member member, long id) {
        Round round = roundRepository.findById(id).orElseThrow();
        UserRound userRound = userRoundRepository.findByMemberAndRound(member, round);
        userRound.changeState();
    }

    public UserRoundCompleteResponse isUploadSuccessful(Member member, long id) {
        Round round = roundRepository.findById(id).orElseThrow();
        if (userRoundRepository.existsByMemberAndRound(member, round)) {
            return new UserRoundCompleteResponse(true);
        }
        return new UserRoundCompleteResponse(false);
    }

    public UserRoundUploadStatus isSuccessful(Member member, Round round) {
        UserRound userRound = userRoundRepository.findByMemberAndRound(member, round);
        if (userRound.isProgressState()) {
            return UserRoundUploadStatus.True;
        }
        return UserRoundUploadStatus.False;
    }
}
