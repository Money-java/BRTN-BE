package com.example.backend.ImageUpload;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Slf4j

@Service
public class ImageUploadService {
    private final AmazonS3Client s3Client;

    @Autowired
    public ImageUploadService(AmazonS3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxSizeString;

    // 파일 삭제
    public void deleteFile(String fileUrl) {
        String[] urlParts = fileUrl.split("/");
        String fileBucket = urlParts[2].split("\\.")[0];

        if (!fileBucket.equals(bucket)) {
            log.info("NO_IMAGE_EXIST");
            throw new IllegalArgumentException("No image exist");
        }

        String objectKey = fileUrl.substring(fileUrl.indexOf(bucket) + bucket.length() + 1);

        if (!s3Client.doesObjectExist(bucket, objectKey)) {
            log.info("NO_IMAGE_EXIST");
            throw new IllegalArgumentException("No image exist");
        }

        try {
            s3Client.deleteObject(bucket, objectKey);
        } catch (AmazonS3Exception e) {
            log.error("File delete fail : " + e.getMessage());
            throw new AmazonClientException("FAIL_DELETE");
        } catch (SdkClientException e) {
            log.error("AWS SDK client error : " + e.getMessage());
            throw new AmazonClientException("FAIL_DELETE");
        }

        log.info("File delete complete: " + objectKey);
    }

    // 단일 파일 저장
    public String saveFile(MultipartFile file) {
        long startTime = System.currentTimeMillis();
        String randomFilename = generateRandomFilename(file);

        validateFileSize(file);

        log.info("File upload started: {}, size: {} bytes", randomFilename, file.getSize());

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());

        try {
            s3Client.putObject(bucket, randomFilename, file.getInputStream(), metadata);
        } catch (AmazonS3Exception e) {
            log.error("Amazon S3 error while uploading file: " + e.getMessage());
            throw new AmazonS3Exception("FAIL_UPLOAD");
        } catch (SdkClientException e) {
            log.error("AWS SDK client error while uploading file: " + e.getMessage());
            throw new AmazonClientException("FAIL_UPLOAD");
        } catch (IOException e) {
            log.error("IO error while uploading file: " + e.getMessage());
            throw new AmazonServiceException("FAIL_UPLOAD");
        }

        long duration = System.currentTimeMillis() - startTime;
        log.info("File upload completed: {} in {} ms", randomFilename, duration);

        return s3Client.getUrl(bucket, randomFilename).toString();
    }

    // 랜덤파일명 생성 (파일명 중복 방지)
    private String generateRandomFilename(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileExtension = validateFileExtension(originalFilename);
        return UUID.randomUUID() + "." + fileExtension;
    }

    // 파일 확장자 체크
    private String validateFileExtension(String originalFilename) {
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
        List<String> allowedExtensions = Arrays.asList("jpg", "png", "gif", "jpeg");

        if (!allowedExtensions.contains(fileExtension)) {
            throw new IllegalArgumentException("File extension not supported: " + fileExtension);
        }

        return fileExtension;
    }

    // 파일 크기 검증
    private void validateFileSize(MultipartFile file) {
        DataSize maxFileSize = DataSize.parse(maxSizeString);
        if (file.getSize() > maxFileSize.toBytes()) {
            log.info("File is too large");
            throw new IllegalArgumentException("File size exceeds the maximum limit of " + maxSizeString);
        }
    }
}
