package com.kodachaya.gourmet.api.service.storage;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
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

    private static final String END_POINT = "http://kr.objectstorage.ncloud.com";
    private static final String REGION_NAME = "kr-standard";
    private static final String ACCESS_KEY = "TL6S1GwkQuftdnGQykDn";
    private static final String SECRET_KEY = "gVYnWSwDv7OG8ZL8EpuxS6aQoB6zUJVkxmiRTzri";

    private static final String BUCKET_NAME = "gourmet";
    final AmazonS3 S3;

    public StorageServiceImpl() {
        S3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(END_POINT, REGION_NAME))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
                .build();

    }

    @Override
    public String uploadProfile(MultipartFile multipartFile) throws FileNotFoundException{

        // create folder
        UUID uuid = UUID.randomUUID();
        String UUIDStringFilePath = uuid.toString();

        // upload local file
        String profileImageUrl = UUIDStringFilePath + ".jpg";

        File file = new File(profileImageUrl);
        FileOutputStream fos;

        fos = new FileOutputStream(file);
        try {
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, "profile/" + UUIDStringFilePath, file);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            S3.putObject(putObjectRequest);
            file.delete();
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

        return END_POINT + "/" + BUCKET_NAME + "/profile/" + UUIDStringFilePath;
    }

    @Override
    public List<String> uploadMenuImages(List<MultipartFile> files) throws FileNotFoundException{
        // create folder
        String folderName = "menu";

        UUID uuid = UUID.randomUUID();
        String UUIDStringFilePath = uuid.toString();

        // upload local file
        String menuImageUrl = UUIDStringFilePath + ".jpg";

        File file = new File(menuImageUrl);
        FileOutputStream fos;

        fos = new FileOutputStream(file);
        try {
            for (MultipartFile i : files) {
                fos.write(i.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            PutObjectRequest putObjectRequest =
                    new PutObjectRequest(BUCKET_NAME, "menu/" + UUIDStringFilePath, file);
            putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
            S3.putObject(putObjectRequest);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        }

        String menuImageFileUrl;
        menuImageFileUrl = END_POINT + "/" + BUCKET_NAME + "/menu/" + UUIDStringFilePath;

        List<String> menuImageFileUrlList = new ArrayList<>();
        menuImageFileUrlList.add(menuImageFileUrl);

        return menuImageFileUrlList;
    }
}
