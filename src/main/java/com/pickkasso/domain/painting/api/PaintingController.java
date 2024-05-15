// PaintingController
package com.pickkasso.domain.painting.api;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.painting.domain.Painting;
import com.pickkasso.domain.painting.dto.AddPaintingRequest;
import com.pickkasso.domain.painting.dto.AllPaintingListViewResponse;
import com.pickkasso.domain.painting.dto.UserPaintingListViewResponse;
import com.pickkasso.domain.painting.service.PaintingService;
import com.pickkasso.global.util.MemberUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paintings")
@RequiredArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;
    private final MemberUtil memberUtil;

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
        return paintingService.uploadPainting(file, addPaintingRequest, member.getId());
    }

    @DeleteMapping
    public void deletePainting(Long paintingId) throws IOException {
        paintingService.deletePainting(paintingId);
    }
}
