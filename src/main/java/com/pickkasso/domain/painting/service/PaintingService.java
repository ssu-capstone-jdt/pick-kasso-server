// PaintingService
package com.pickkasso.domain.painting.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.pickkasso.domain.painting.dto.UserPaintingListViewResponse;
import com.pickkasso.domain.round.dao.RoundRepository;
import com.pickkasso.domain.round.domain.Round;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PaintingService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired private AmazonS3 s3Client;

    @Autowired private PaintingRepository paintingRepository;

    private final RoundRepository roundRepository;
    private final CurriculumRepository curriculumRepository;
    private final MemberRepository memberRepository;

    public Painting uploadPainting(
            MultipartFile file, AddPaintingRequest addPaintingRequest, Long memberId)
            throws IOException {
        Painting painting = addPaintingRequest.toEntity();
        String fileName = file.getOriginalFilename();
        String fileUrl = "https://s3.amazonaws.com/" + bucket + fileName;
        painting.setPaintingLink(fileUrl);
        painting.setMemberId(memberId);
        painting.setPaintingName(fileName);
        paintingRepository.save(painting);

        // try {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        s3Client.putObject(bucket, fileName, file.getInputStream(), metadata);
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //            return HttpStatus.INTERNAL_SERVER_ERROR.toString();
        //        }
        return paintingRepository.save(painting);
    }

    public void deletePainting(Long paintingId) throws IOException {
        Painting painting = paintingRepository.findById(paintingId).orElseThrow();
        try {
            s3Client.deleteObject(bucket, painting.getPaintingName());
        } catch (SdkClientException e) {
            throw new IOException("Error deleting file");
        }
        //        Painting painting =
        //        paintingRepository.delete();
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

    // addPainting
    //    public String uploadPainting(MultipartFile paintingFile, String title) {
    //
    //        String fileName = generateFileName(paintingFile);
    //        try {
    //            ObjectMetadata metadata = new ObjectMetadata();
    //            metadata.setContentLength(paintingFile.getSize());
    //            metadata.addUserMetadata("title", title); // Adding title as metadata
    //            s3Client.putObject(
    //                    new PutObjectRequest(bucket, fileName, convert(paintingFile), metadata));
    //            return "Image uploaded successfully!";
    //        } catch (IOException e) {
    //            e.printStackTrace();
    //            return "Failed to upload image.";
    //        }
    //    }
    //
    //    private String generateFileName(MultipartFile file) {
    //        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
    //    }
    //
    //    private InputStream convert(MultipartFile file) throws IOException {
    //        return file.getInputStream();
    //    }
    //
    // deletePainting
    //        public void deletePainting(Long userId) {
    //            Painting painting =
    //                    paintingRepository
    //                            .findById(userId)
    //                            .orElseThrow(
    //                                    () ->
    //                                            new RuntimeException(
    //                                                    "Painting not found with id: " + userId));
    //
    //            String objectKey = painting.getPaintingLink();
    //
    //            final AmazonS3 s3 =
    //
    // AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    //
    //            try {
    //                // 객체 삭제
    //                s3.deleteObject(bucket, objectKey);
    //                System.out.println("Object deleted successfully.");
    //            } catch (AmazonServiceException e) {
    //                // 에러 발생 시 예외 처리
    //                System.err.println("Error occurred: " + e.getErrorMessage());
    //                System.exit(1);
    //            }
    //
    //        paintingRepository.delete(painting);
    //    }

    //    public List<AllPaintingListViewResponse> getAllPaintings(){
    //        List<Painting> paintings = paintingRepository.findByState(true);
    //
    //        // 각 그림에 대한 정보를 담은 DTO를 생성
    //        return paintings.stream()
    //                .map(painting -> {
    //                    Member user = userService.getUserById(painting.getUserId());
    //                    Curriculum curriculum =
    // curriculumService.getCurriculumById(painting.getCurriculumId());
    //
    //                    // painting, user, curriculum 정보를 이용하여 DTO를 생성
    //                    return new AllPaintingListViewResponse(
    //                            painting.getPaintingLink(),
    //                            painting.getPaintingTitle(),
    //                            curriculum.getCurriculumTitle(),
    //                            curriculum.getCurriculumInfo(),
    //                            //painting.getCreatedAt(),
    //                            user.getNickname(),
    //                            //user.getUserProfileUrl()
    //                    );
    //                })
    //                .collect(Collectors.toList());
    //    }
    //
    //
    //    public List<UserPaintingListViewResponse> getUsersPaintings(Long userId) {
    //        List<Painting> paintings = paintingRepository.findByUserId(userId);
    //        Curriculum curriculum = curriculumRepository.findById();
    //        return paintings.stream()
    //                .map(painting -> new UserPaintingListViewResponse(painting, curriculum))
    //                .collect(Collectors.toList());
    //    }

}
