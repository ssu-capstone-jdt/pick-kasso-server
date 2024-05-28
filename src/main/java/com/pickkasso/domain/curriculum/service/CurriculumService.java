package com.pickkasso.domain.curriculum.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.dto.request.AddCurriculumRequest;
import com.pickkasso.domain.curriculum.dto.response.AddCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.response.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.response.CurriculumResponse;
import com.pickkasso.domain.curriculum.dto.response.SelectedCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.response.UserCurriculumListViewResponse;
import com.pickkasso.domain.curriculumBackground.service.CurriculumBackgroundService;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.painting.service.PaintingService;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.round.dto.RoundResponse;
import com.pickkasso.domain.round.service.RoundService;
import com.pickkasso.domain.userRound.dto.UserRoundResponse;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.domain.usercurriculum.domain.StateType;
import com.pickkasso.domain.usercurriculum.domain.UserCurriculum;
import com.pickkasso.domain.usercurriculum.dto.response.DeleteUserCurriculumResponse;
import com.pickkasso.domain.usercurriculum.dto.response.DownloadCurriculumResponse;
import com.pickkasso.domain.usercurriculum.service.UserCurriculumService;
import com.pickkasso.global.error.exception.CustomException;
import com.pickkasso.global.error.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CurriculumService {
    private final CurriculumRepository curriculumRepository;
    private final UserCurriculumService userCurriculumService;
    private final RoundService roundService;
    private final UserRoundService userRoundService;

    private final PaintingService paintingService;

    public List<AllCurriculumListViewResponse> getAllCurriculums(Member member) {
        List<Curriculum> curriculums = curriculumRepository.findAll();
        List<UserCurriculum> userCurriculums = userCurriculumService.findByMember(member);
        List<AllCurriculumListViewResponse> allCurriculumListViewResponses = new ArrayList<>();

        for (Curriculum curriculum : curriculums) {
            AllCurriculumListViewResponse allCurriculumListViewResponse =
                    new AllCurriculumListViewResponse();
            allCurriculumListViewResponse.setCurriculumResponse(new CurriculumResponse(curriculum));
            allCurriculumListViewResponse.setState(StateType.Pending);

            for (UserCurriculum userCurriculum : userCurriculums) {
                if (userCurriculum.getCurriculum().getId().equals(curriculum.getId())) {
                    allCurriculumListViewResponse.setState(userCurriculum.getState());
                    break;
                }
            }
            allCurriculumListViewResponses.add(allCurriculumListViewResponse);
        }

        return allCurriculumListViewResponses;
    }

    public DownloadCurriculumResponse downloadCurriculum(Long currId) {
        Curriculum curr = getCurriculum(currId);
        UserCurriculum userCurriculum = userCurriculumService.saveUserCurriculum(curr);
        return new DownloadCurriculumResponse(
                userCurriculum.getMember().getNickname(), curr.getCurriculumTitle());
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

    public DeleteUserCurriculumResponse deleteUserCurriculum(Long currId) {
        Curriculum curriculum = getCurriculum(currId);
        return userCurriculumService.deleteUserCurriculum(curriculum);
    }

    private Curriculum getCurriculum(Long currId) {
        return curriculumRepository
                .findById(currId)
                .orElseThrow(() -> new CustomException(ErrorCode.CURR_NOT_FOUND));
    }

    public AddCurriculumResponse addCurriculum(MultipartFile file, AddCurriculumRequest request)
            throws IOException {

        String fileUrl = paintingService.uploadPainting(file);
        Curriculum curriculum =
                Curriculum.createCurriculum(
                        request.getCurrTitle(),
                        request.getCurrInfo(),
                        request.getCurrExplanation(),
                        request.getCurrRoundCount(),
                        request.getCurrDifficulty(),
                        fileUrl);

        List<Round> rounds = new ArrayList<>();
        List<Integer> times = new ArrayList<>();
        List<String> explanations = new ArrayList<>();

        for (int i = 0; i < request.getCurrRoundCount(); i++) {
            times.add(request.getTimes().get(i));
            explanations.add(request.getExplanations().get(i));
        }

        for (int i = 0; i < times.size(); i++) {
            rounds.add(roundService.setRound(curriculum, i + 1, times.get(i), explanations.get(i)));
        }
        curriculum.setRounds(rounds);

        return new AddCurriculumResponse(curriculumRepository.save(curriculum));
    }
}
