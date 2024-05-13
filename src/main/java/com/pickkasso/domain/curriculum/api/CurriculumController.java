package com.pickkasso.domain.curriculum.api;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.SelectedCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.UserCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.service.CurriculumService;
import com.pickkasso.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/curriculums")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumService curriculumService;

    @GetMapping
    public List<UserCurriculumListViewResponse> findUserCurriculums(
            @AuthenticationPrincipal Member member) {
        List<UserCurriculumListViewResponse> userCurriculumListViewResponses =
                curriculumService.getUserCurriculums(member);
        return userCurriculumListViewResponses;
    }

    @GetMapping("/all")
    public List<AllCurriculumListViewResponse> findAllCurriculums(
            @AuthenticationPrincipal Member member) {
        List<AllCurriculumListViewResponse> allCurriculumListViewResponses =
                curriculumService.getAllCurriculums(member);
        return allCurriculumListViewResponses;
    }

    @GetMapping("/{id}")
    public SelectedCurriculumResponse findCurriculum(
            @PathVariable long id, @AuthenticationPrincipal Member member) {

        SelectedCurriculumResponse selectedCurriculumResponse =
                curriculumService.getSelectedCurriculum(id, member);

        return selectedCurriculumResponse;
    }

    @PostMapping("/{id}")
    public void downloadCurriculum(
            @PathVariable Long curriculumId, @AuthenticationPrincipal Member member) {
        curriculumService.downloadCurriculum(curriculumId, member);
    }

    @DeleteMapping("/{id}")
    public void deleteCurriculum(@PathVariable long id) {
        curriculumService.delete(id);
    }
}
