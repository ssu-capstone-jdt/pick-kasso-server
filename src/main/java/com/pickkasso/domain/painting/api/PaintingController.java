// PaintingController
package com.pickkasso.domain.painting.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.member.dao.MemberRepository;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.painting.dao.PaintingRepository;
import com.pickkasso.domain.painting.domain.Painting;
import com.pickkasso.domain.painting.dto.AddPaintingRequest;
import com.pickkasso.domain.painting.dto.AllPaintingListViewResponse;
import com.pickkasso.domain.painting.dto.StampListViewResponse;
import com.pickkasso.domain.painting.dto.UserPaintingListViewResponse;
import com.pickkasso.domain.painting.service.PaintingService;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paintings")
@RequiredArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;
    private final MemberUtil memberUtil;

    private final PaintingRepository paintingRepository;
    private final RoundRepository roundRepository;
    private final CurriculumRepository curriculumRepository;
    private final MemberRepository memberRepository;

    @GetMapping
    public List<UserPaintingListViewResponse> findUsersPaintings() {
        Member member = memberUtil.getCurrentMember();
        return paintingService.getUsersPaintings(member.getId());
    }

    @GetMapping("/all")
    public List<AllPaintingListViewResponse> findAllPaintings() {
        return paintingService.getAllPaintings();
    }

    @PostMapping
    public Painting uploadFile(
            @RequestParam("file") MultipartFile file, AddPaintingRequest addPaintingRequest)
            throws IOException {
        final Member member = memberUtil.getCurrentMember();
        return paintingService.addPainting(file, addPaintingRequest, member);
    }

    @DeleteMapping("/{paintingId}")
    public void deletePainting(@PathVariable Long paintingId) throws IOException {
        final Member member = memberUtil.getCurrentMember();
        paintingService.deletePainting(member, paintingId);
    }

    @GetMapping(value = "/")
    public List<StampListViewResponse> getUsersStamp() {
        Member member = memberUtil.getCurrentMember();
        return paintingService.getUsersStamp(member.getId());
    }
}
