// PaintingService
package com.pickkasso.domain.painting.service;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
import com.pickkasso.domain.curriculum.domain.Curriculum;
import com.pickkasso.domain.member.dao.MemberRepository;
import com.pickkasso.domain.member.domain.Member;
import com.pickkasso.domain.painting.dao.PaintingRepository;
import com.pickkasso.domain.painting.domain.Painting;
import com.pickkasso.domain.painting.dto.AddPaintingRequest;
import com.pickkasso.domain.painting.dto.AllPaintingListViewResponse;
import com.pickkasso.domain.painting.dto.StampListViewResponse;
import com.pickkasso.domain.painting.dto.UserPaintingListViewResponse;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;
import com.pickkasso.domain.userRound.service.UserRoundService;
import com.pickkasso.global.error.exception.RoundNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaintingService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 s3Client;

    private final PaintingRepository paintingRepository;

    private final RoundRepository roundRepository;
    private final CurriculumRepository curriculumRepository;
    private final MemberRepository memberRepository;

    private final UserRoundService userRoundService;
    private final GoogleDriveService googleDriveService;

    public Painting addPainting(
            MultipartFile file, AddPaintingRequest addPaintingRequest, Member member)
            throws IOException {
        Painting painting = addPaintingRequest.toEntity();
        String fileName = file.getOriginalFilename();
        String fileUrl = uploadPainting(file);

        painting.setPaintingLink(fileUrl);
        painting.setMemberId(member.getId());
        painting.setPaintingName(fileName);

        if (!roundRepository.existsById(painting.getRoundId())) {
            throw new RoundNotFoundException(painting.getRoundId());
        }

        userRoundService.changeUserRoundStateUpload(member, addPaintingRequest.getRoundId());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

        byte[] fileContent = file.getBytes();
        googleDriveService.uploadToGoogleDrive(member.getId(), fileContent);

        return paintingRepository.save(painting);
    }

    public String uploadPainting(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        URL url = s3Client.getUrl(bucket, fileName);
        String fileUrl = url.toString();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);

        return fileUrl;
    }

    public void deletePainting(Member member, Long paintingId) throws IOException {
        Painting painting = paintingRepository.findById(paintingId).orElseThrow();
        userRoundService.changeUserRoundStateDelete(member, painting.getRoundId());

        try {
            s3Client.deleteObject(bucket, painting.getPaintingName());
        } catch (SdkClientException e) {
            throw new IOException("Error deleting file");
        }
    }

    public List<AllPaintingListViewResponse> getAllPaintings() {
        List<Painting> paintings = paintingRepository.findByPaintingState(true);
        List<AllPaintingListViewResponse> allPaintingListViewResponses = new ArrayList<>();
        for (Painting painting : paintings) {
            Round round = roundRepository.findById(painting.getRoundId()).orElseThrow();
            Curriculum curriculum =
                    curriculumRepository.findById(round.getCurriculum().getId()).orElseThrow();
            Member member = memberRepository.findById(painting.getMemberId()).orElseThrow();
            AllPaintingListViewResponse allPaintingListViewResponse =
                    new AllPaintingListViewResponse(
                            painting.getPaintingLink(),
                            member.getNickname(),
                            painting.getPaintingLink(),
                            painting.getPaintingTitle(),
                            curriculum.getCurriculumTitle(),
                            curriculum.getCurriculumInfo());
            allPaintingListViewResponses.add(allPaintingListViewResponse);
        }
        return allPaintingListViewResponses;
    }

    public List<UserPaintingListViewResponse> getUsersPaintings(Long memberId) {
        List<Painting> paintings = paintingRepository.findByMemberId(memberId);
        List<UserPaintingListViewResponse> userPaintingListViewResponses = new ArrayList<>();
        for (Painting painting : paintings) {
            Round round = roundRepository.findById(painting.getRoundId()).orElseThrow();
            Curriculum curriculum =
                    curriculumRepository.findById(round.getCurriculum().getId()).orElseThrow();
            Member member = memberRepository.findById(painting.getMemberId()).orElseThrow();
            UserPaintingListViewResponse userPaintingListViewResponse =
                    new UserPaintingListViewResponse(painting, curriculum);
            userPaintingListViewResponses.add(userPaintingListViewResponse);
        }
        return userPaintingListViewResponses;
    }

    public List<StampListViewResponse> getUsersStamp(Long memberId) {
        List<Painting> paintings = paintingRepository.findByMemberId(memberId);
        List<StampListViewResponse> stampListViewResponses = new ArrayList<>();

        for (Painting painting : paintings) {
            LocalDateTime localDateTime = painting.getCreatedAt();
            stampListViewResponses.add(new StampListViewResponse(localDateTime));
        }
        return stampListViewResponses;
    }

    public void changeMemberIdPaintings(Member member) {
        List<Painting> paintings = paintingRepository.findByMemberId(member.getId());
        for (Painting painting : paintings) {
            painting.setMemberId(0L);
            paintingRepository.save(painting);
        }
    }
}
