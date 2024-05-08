package com.pickkasso.domain.curriculum.api;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.CurriculumResponse;
import com.pickkasso.domain.curriculum.service.CurriculumService;
import com.pickkasso.domain.member.domain.Member;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/curriculums")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumService curriculumService;

    @GetMapping
    public List<CurriculumResponse> findUserCurriculums(@AuthenticationPrincipal Member member) {
        List<CurriculumResponse> allCurriculumRespons =
                curriculumService.getUserCurriculums(member).stream()
                        .map(CurriculumResponse::new)
                        .toList();
        return allCurriculumRespons;
    }

    @GetMapping("/all")
    public List<AllCurriculumListViewResponse> findAllCurriculums(
            @AuthenticationPrincipal Member member) {
        List<AllCurriculumListViewResponse> allCurriculumListViewResponses =
                curriculumService.getAllCurriculums(member);
        return allCurriculumListViewResponses;
    }

    // round 추가 필요
    @GetMapping("/{id}")
    public CurriculumResponse findCurriculum(@PathVariable long id) {
        Curriculum curriculum = curriculumService.findById(id);
        CurriculumResponse curriculumResponse = new CurriculumResponse(curriculum);

        return curriculumResponse;
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
