 package com.pickkasso.domain.painting.service;

 import java.io.IOException;
 import java.io.InputStream;
 import java.util.UUID;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Service;
 import org.springframework.web.multipart.MultipartFile;

 import com.amazonaws.AmazonServiceException;
 import com.amazonaws.regions.Regions;
 import com.amazonaws.services.s3.AmazonS3;
 import com.amazonaws.services.s3.AmazonS3ClientBuilder;
 import com.amazonaws.services.s3.model.ObjectMetadata;
 import com.amazonaws.services.s3.model.PutObjectRequest;
 import com.pickkasso.domain.curriculum.dao.CurriculumRepository;
 import com.pickkasso.domain.painting.dao.PaintingRepository;
 import com.pickkasso.domain.painting.domain.Painting;

 import lombok.RequiredArgsConstructor;

 @RequiredArgsConstructor
 @Service
 public class PaintingService {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired private AmazonS3 s3Client;

    @Autowired private PaintingRepository paintingRepository;

    @Autowired private CurriculumRepository curriculumRepository;

    // addPainting
    public String uploadPainting(MultipartFile paintingFile, String title) {

        String fileName = generateFileName(paintingFile);
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(paintingFile.getSize());
            metadata.addUserMetadata("title", title); // Adding title as metadata
            s3Client.putObject(
                    new PutObjectRequest(bucket, fileName, convert(paintingFile), metadata));
            return "Image uploaded successfully!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload image.";
        }
    }

    private String generateFileName(MultipartFile file) {
        return UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
    }

    private InputStream convert(MultipartFile file) throws IOException {
        return file.getInputStream();
    }

    // deletePainting
    public void deletePainting(Long userId) {
        Painting painting =
                paintingRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Painting not found with id: " + userId));

        String objectKey = painting.getPaintingLink();

        final AmazonS3 s3 =
                AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();

        try {
            // 객체 삭제
            s3.deleteObject(bucket, objectKey);
            System.out.println("Object deleted successfully.");
        } catch (AmazonServiceException e) {
            // 에러 발생 시 예외 처리
            System.err.println("Error occurred: " + e.getErrorMessage());
            System.exit(1);
        }

        paintingRepository.delete(painting);
    }

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
