package com.pickkasso.domain.painting.api;


import com.pickkasso.domain.painting.dto.AddPaintingRequest;
import com.pickkasso.domain.painting.dto.AllPaintingListViewResponse;
import com.pickkasso.domain.painting.dto.UserPaintingListViewResponse;
import com.pickkasso.domain.painting.service.PaintingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/paintings")
@RequiredArgsConstructor
public class PaintingController {

    private final PaintingService paintingService;


//    @GetMapping
//    public List<UserPaintingListViewResponse> getUsersPaintings(@AuthenticationPrincipal Long userId){
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
    public ResponseEntity<AddPaintingRequest> addPainting(@RequestParam MultipartFile paintingFile,
                                                          @RequestParam("title") String title) {
        String paintingUrl = paintingService.uploadPainting(paintingFile, title);
        AddPaintingRequest addPaintingRequest = new AddPaintingRequest(paintingUrl, title, true);

        return ResponseEntity.status(HttpStatus.OK).body(addPaintingRequest);
    }



    @DeleteMapping
    public String deletePainting(@AuthenticationPrincipal Long userId) {
        paintingService.deletePainting(userId);
        return "Painting deleted successfully!";
    }

}

