package com.pickkasso.domain.painting.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.pickkasso.domain.painting.dto.AddPaintingRequest;
import com.pickkasso.domain.painting.service.PaintingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/paintings")
@RequiredArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;

    //    @GetMapping
    //    public List<UserPaintingListViewResponse> getUsersPaintings(@AuthenticationPrincipal Long
    // userId){
    //
    //        return paintingService.getUsersPaintings(userId);
    //    }
    //
    //    @GetMapping("/all")
    //    public List<AllPaintingListViewResponse> findAllPaintings(){
    //
    //        return paintingService.getAllPaintings();
    //    }

    @PostMapping
    public AddPaintingRequest addPainting(
            @RequestParam MultipartFile paintingFile, @RequestParam("title") String title) {
        String paintingUrl = paintingService.uploadPainting(paintingFile, title);
        AddPaintingRequest addPaintingRequest = new AddPaintingRequest(paintingUrl, title, true);

        return addPaintingRequest;
    }

    @DeleteMapping
    public void deletePainting(@AuthenticationPrincipal Long userId) {
        paintingService.deletePainting(userId);
    }
}
