package com.pickkasso.domain.curriculum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.pickkasso.domain.curriculum.dto.SelectedCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.UserCurriculumListViewResponse;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.domain.usercurriculum.service.UserCurriculumService;
import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.CurriculumResponse;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.domain.StateType;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private UserCurriculumService userCurriculumService;
    private RoundService roundService;
    private UserRoundService userRoundService;

    //코드 정리 필요
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

    //예외 따로 처리
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
        for(UserCurriculum userCurriculum : userCurriculums) {
            Curriculum curriculum = userCurriculum.getCurriculum();
            CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);
            StateType stateType = userCurriculum.getState();
            UserCurriculumListViewResponse userCurriculumListViewResponse = new UserCurriculumListViewResponse(curriculumResponse, stateType);
            userCurriculumListViewResponses.add(userCurriculumListViewResponse);
        }
        return userCurriculumListViewResponses;
    }

    public SelectedCurriculumResponse getSelectedCurriculum(long id, Member member){
        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Curriculum not found with id: " + id));

        CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);
        RoundResponse roundResponse = roundService.getRound(curriculum);
        UserRoundResponse userRoundResponse = userRoundService.getUserRound(member,curriculum);
        SelectedCurriculumResponse selectedCurriculumResponse = new SelectedCurriculumResponse(curriculumResponse, roundResponse, userRoundResponse);

        return selectedCurriculumResponse;
    }

    public void delete(long id) {
        curriculumRepository.deleteById(id);
    }

    public CurriculumResponse findById(long id) {
        Curriculum curriculum = curriculumRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Curriculum not found with id: " + id));

        CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);
        return curriculumResponse;
    }
}
