package com.kodachaya.gourmet.api.service.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

public interface StorageService {


    String uploadProfile(MultipartFile file) throws FileNotFoundException;


    List<String> uploadMenuImages(List<MultipartFile> files) throws FileNotFoundException;

}
