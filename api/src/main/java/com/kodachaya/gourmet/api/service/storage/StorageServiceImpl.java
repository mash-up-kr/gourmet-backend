package com.kodachaya.gourmet.api.service.storage;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StorageServiceImpl implements StorageService {

    private final String endPoint = "http://kr.objectstorage.ncloud.com";
    private final String regionName = "kr-standard";
    private final String accessKey = "TL6S1GwkQuftdnGQykDn";
    private final String secretKey = "gVYnWSwDv7OG8ZL8EpuxS6aQoB6zUJVkxmiRTzri";


    final AmazonS3 s3;

    public StorageServiceImpl() {
        s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

    }

    @Override
    public String uploadProfile(MultipartFile multipartFile) throws FileNotFoundException{
        // S3 client

        String bucketName = "gourmet";

        // create folder
        String folderName = "profile";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("multipart/form-data");
        // 폴더 생성
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName + "/", new ByteArrayInputStream(new byte[0]),objectMetadata);
        // s3에 폴더 생성하여 넣기
        s3.putObject(putObjectRequest);
        System.out.format("Folder %s has been created.\n", folderName);

        UUID uuid = UUID.randomUUID();
        String UUIDStringFilePath = uuid.toString();

        // upload local file
        String profileImageName = "profileImage";
        String profileImageUrl = "/profile/" + profileImageName + UUIDStringFilePath + ".jpg";
        File file = new File(profileImageUrl);
        FileOutputStream fos;

        fos = new FileOutputStream(file);
        try {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        s3.putObject(bucketName, profileImageName, file);
        System.out.format("Object %s has been created.\n", profileImageName);

        return profileImageUrl;
    }

    @Override
    public List<String> uploadMenuImages(List<MultipartFile> files) throws FileNotFoundException{
        // S3 client

        String bucketName = "gourmet";

        // create folder
        String folderName = "menu";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("multipart/form-data");
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, folderName, new ByteArrayInputStream(new byte[0]), objectMetadata);

        s3.putObject(putObjectRequest);
        System.out.format("Folder %s has been created.\n", folderName);


        UUID uuid = UUID.randomUUID();
        String UUIDStringFilePath = uuid.toString();

        // upload local file
        String menuImageName = "menuImagesUrl";
        String menuImagesUrl = "/menu/" + menuImageName + UUIDStringFilePath + ".jpg";
        File file = new File(menuImagesUrl);
        FileOutputStream fos;
        fos = new FileOutputStream(file);

        try {
            fos.write(files.size());
        } catch (IOException e) {
            e.printStackTrace();
        }

        s3.putObject(bucketName, menuImageName, file);
        System.out.format("Object %s has been created.\n", menuImageName);

        List<String> menuImageUrlList = new ArrayList<>();
        menuImageUrlList.add(menuImagesUrl);

        return menuImageUrlList;
    }
}
