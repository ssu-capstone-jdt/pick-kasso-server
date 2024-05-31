package com.pickkasso.domain.round.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.DownloadRoundResponse;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.round.dto.UserRoundUploadStatus;
import com.pickkasso.domain.userRound.domain.UserRound;
import com.pickkasso.domain.userRound.service.UserRoundService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoundService {
    private final RoundRepository roundRepository;
    private final UserRoundService userRoundService;

    public List<RoundResponse> getRound(Curriculum curriculum) {
        List<Round> rounds = roundRepository.findByCurriculum(curriculum);
        List<RoundResponse> roundResponses =
                rounds.stream()
                        .map(
                                round ->
                                        new RoundResponse(
                                                round.getRoundSeq(),
                                                round.getTime(),
                                                round.getExplanation(),
                                                round.getId()))
                        .collect(Collectors.toList());

        return roundResponses;
    }

    public List<DownloadRoundResponse> getDownloadedRound(Member member, Curriculum curriculum) {
        List<Round> rounds = roundRepository.findByCurriculum(curriculum);
        List<DownloadRoundResponse> downloadRoundResponses = new ArrayList<>();

        if (userRoundService.isDownload(member, curriculum)) {
            downloadRoundResponses =
                    rounds.stream()
                            .map(
                                    round ->
                                            new DownloadRoundResponse(
                                                    round.getRoundSeq(),
                                                    round.getTime(),
                                                    round.getExplanation(),
                                                    round.getId(),
                                                    userRoundService.isSuccessful(member, round)))
                            .collect(Collectors.toList());
        } else {
            downloadRoundResponses =
                    rounds.stream()
                            .map(
                                    round ->
                                            new DownloadRoundResponse(
                                                    round.getRoundSeq(),
                                                    round.getTime(),
                                                    round.getExplanation(),
                                                    round.getId(),
                                                    UserRoundUploadStatus.Null))
                            .collect(Collectors.toList());
        }

        return downloadRoundResponses;
    }


    public Round setRound(Curriculum curriculum, int order, int time, String explanation) {
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
