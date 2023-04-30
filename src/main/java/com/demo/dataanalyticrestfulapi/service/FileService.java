package com.demo.dataanalyticrestfulapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface FileService {
    String UploadImage(String path , MultipartFile file) throws IOException;
}
