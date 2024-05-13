package com.pickkasso.domain.curriculum.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.CurriculumResponse;
import com.pickkasso.domain.curriculum.dto.SelectedCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.UserCurriculumListViewResponse;
import com.pickkasso.domain.curriculumBackground.domain.CurriculumBackground;
import com.pickkasso.domain.curriculumBackground.service.CurriculumBackgroundService;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.domain.usercurriculum.domain.StateType;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import com.pickkasso.domain.usercurriculum.service.UserCurriculumService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final UserCurriculumService userCurriculumService;
    private final CurriculumBackgroundService curriculumBackgroundService;
    private final RoundService roundService;
    private final UserRoundService userRoundService;

    public List<AllCurriculumListViewResponse> getAllCurriculums(Member member) {
        List<Curriculum> curriculums = curriculumRepository.findAll();
        List<UserCurriculum> userCurriculums = userCurriculumService.findByMember(member);
        List<AllCurriculumListViewResponse> allCurriculumListViewResponses = new ArrayList<>();
        for (Curriculum curriculum : curriculums) {
            AllCurriculumListViewResponse allCurriculumListViewResponse =
                    new AllCurriculumListViewResponse();
            allCurriculumListViewResponse.setCurriculumResponse(new CurriculumResponse(curriculum));

            for (UserCurriculum userCurriculum : userCurriculums) {
                if (userCurriculum.getCurriculum().getId() == curriculum.getId()) {
                    allCurriculumListViewResponse.setState(userCurriculum.getState());
                } else {
                    allCurriculumListViewResponse.setState(StateType.Pending);
                }
            }

            allCurriculumListViewResponses.add(allCurriculumListViewResponse);
        }
        return allCurriculumListViewResponses;
    }

    public void downloadCurriculum(Long curriculumId, Member member) {
        Curriculum curriculum =
                curriculumRepository
                        .findById(curriculumId)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Curriculum not found with id: " + curriculumId));

        UserCurriculum userCurriculum = userCurriculumService.getUserCurriculum(member, curriculum);

        userCurriculumService.save(userCurriculum);
    }

    public List<UserCurriculumListViewResponse> getUserCurriculums(Member member) {
        List<UserCurriculum> userCurriculums = userCurriculumService.findByMember(member);
        List<UserCurriculumListViewResponse> userCurriculumListViewResponses = new ArrayList<>();
        for (UserCurriculum userCurriculum : userCurriculums) {
            Curriculum curriculum = userCurriculum.getCurriculum();
            CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);
            StateType stateType = userCurriculum.getState();
            UserCurriculumListViewResponse userCurriculumListViewResponse =
                    new UserCurriculumListViewResponse(curriculumResponse, stateType);
            userCurriculumListViewResponses.add(userCurriculumListViewResponse);
        }
        return userCurriculumListViewResponses;
    }

    public SelectedCurriculumResponse getSelectedCurriculum(long id, Member member) {
        Curriculum curriculum =
                curriculumRepository
                        .findById(id)
                        .orElseThrow(
                                () ->
                                        new IllegalArgumentException(
                                                "Curriculum not found with id: " + id));

        CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);
        List<RoundResponse> roundResponses = roundService.getRound(curriculum);
        List<UserRoundResponse> userRoundResponses =
                userRoundService.getUserRound(member, curriculum);
        SelectedCurriculumResponse selectedCurriculumResponse =
                new SelectedCurriculumResponse(
                        curriculumResponse, roundResponses, userRoundResponses);

        return selectedCurriculumResponse;
    }

    public void delete(long id) {
        curriculumRepository.deleteById(id);
    }

    public Curriculum addCurriculum(
            String curriculumTitle,
            String curriculumInfo,
            String curriculumExplanation,
            int curriculumRoundCount,
            String curriculumDifficulty,
            String curriculumBackground,
            List<String> times,
            List<String> explanations) {

        Curriculum curriculum =
                Curriculum.builder()
                        .curriculumTitle(curriculumTitle)
                        .curriculumInfo(curriculumInfo)
                        .curriculumExplanation(curriculumExplanation)
                        .curriculumRoundCount(curriculumRoundCount)
                        .curriculumDifficulty(curriculumDifficulty)
                        .build();

        CurriculumBackground background =
                curriculumBackgroundService.setCurriculumBackground(
                        curriculum, curriculumBackground);

        List<Round> rounds = new ArrayList<>();
        for (int order = 1; order <= times.size(); order++) {
            Round round =
                    roundService.setRound(
                            curriculum, order, times.get(order - 1), explanations.get(order - 1));
            rounds.add(round);
        }

        curriculum.setBackground(background);
        curriculum.setRounds(rounds);

        return curriculumRepository.save(curriculum);
    }
}
