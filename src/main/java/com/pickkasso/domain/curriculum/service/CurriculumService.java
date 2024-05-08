package com.pickkasso.domain.curriculum.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.CurriculumResponse;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.dao.UserCurriculumRepository;
import com.pickkasso.domain.usercurriculum.domain.StateType;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final UserCurriculumRepository userCurriculumRepository;

    public List<AllCurriculumListViewResponse> getAllCurriculums(Member member) {
        List<Curriculum> curriculums = curriculumRepository.findAll();
        List<UserCurriculum> userCurriculums = userCurriculumRepository.findByMember(member);
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

        UserCurriculum userCurriculum = new UserCurriculum();
        userCurriculum.setMember(member);
        userCurriculum.setCurriculum(curriculum);
        userCurriculum.setState(StateType.InProgress);

        userCurriculumRepository.save(userCurriculum);
    }

    public List<Curriculum> getUserCurriculums(Member member) {
        List<UserCurriculum> userCurriculum = userCurriculumRepository.findByMember(member);
        List<Curriculum> curriculumList =
                userCurriculum.stream()
                        .map(UserCurriculum::getCurriculum)
                        .collect(Collectors.toList());

        return curriculumList;
    }

    public void delete(long id) {
        curriculumRepository.deleteById(id);
    }

    public Curriculum findById(long id) {
        return curriculumRepository
                .findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Curriculum not found with id: " + id));
    }
}
