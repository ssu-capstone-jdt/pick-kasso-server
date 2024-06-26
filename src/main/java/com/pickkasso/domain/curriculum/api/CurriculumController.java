package com.pickkasso.domain.curriculum.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.curriculum.dto.request.AddCurriculumRequest;
import com.pickkasso.domain.curriculum.dto.response.*;
import com.pickkasso.domain.curriculum.service.CurriculumService;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.usercurriculum.dto.response.DeleteUserCurriculumResponse;
import com.pickkasso.domain.usercurriculum.dto.response.DownloadCurriculumResponse;
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

    @PostMapping("/{currId}")
    public DownloadCurriculumResponse downloadCurriculum(@PathVariable Long currId) {
        final Member member = memberUtil.getCurrentMember();
        return curriculumService.downloadCurriculum(member, currId);
    }

    @DeleteMapping("/{currId}")
    public DeleteUserCurriculumResponse deleteUserCurriculum(@PathVariable Long currId) {
        final Member member = memberUtil.getCurrentMember();
        return curriculumService.deleteUserCurriculum(member, currId);
    }

    @PostMapping
    public AddCurriculumResponse addCurriculum(
            @RequestParam("file") MultipartFile file,
            @RequestPart("request") AddCurriculumRequest request)
            throws IOException {
        return curriculumService.addCurriculum(file, request);
    }
}
