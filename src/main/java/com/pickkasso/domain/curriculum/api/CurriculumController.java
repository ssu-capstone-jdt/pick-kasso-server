package com.pickkasso.domain.curriculum.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.pickkasso.domain.curriculum.dto.AllCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.dto.SelectedCurriculumResponse;
import com.pickkasso.domain.curriculum.dto.UserCurriculumListViewResponse;
import com.pickkasso.domain.curriculum.service.CurriculumService;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/curriculums")
@RequiredArgsConstructor
public class CurriculumController {
    private final CurriculumService curriculumService;
    private final MemberUtil memberUtil;

    @GetMapping
    public List<UserCurriculumListViewResponse> findUserCurriculums() {
        final Member member = memberUtil.getCurrentMember();

        return curriculumService.getUserCurriculums(member);
    }

    @GetMapping("/all")
    public List<AllCurriculumListViewResponse> findAllCurriculums() {
        final Member member = memberUtil.getCurrentMember();
        return curriculumService.getAllCurriculums(member);
    }

    @GetMapping("/{id}")
    public SelectedCurriculumResponse findCurriculum(@PathVariable long id) {
        final Member member = memberUtil.getCurrentMember();
        return curriculumService.getSelectedCurriculum(id, member);
    }

    @PostMapping("/{id}")
    public void downloadCurriculum(@PathVariable Long curriculumId) {
        final Member member = memberUtil.getCurrentMember();
        curriculumService.downloadCurriculum(curriculumId, member);
    }

    @DeleteMapping("/{id}")
    public void deleteCurriculum(@PathVariable long id) {
        curriculumService.delete(id);
    }
}
