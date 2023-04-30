package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String UploadImage(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String ID = UUID.randomUUID().toString();
        assert name != null;
        String filename = ID.concat(name.substring(name.lastIndexOf(".")));

        String filePath = path + File.separator + filename;

        File fileupload = new File(path);
        if (!fileupload.exists()){
            fileupload.mkdirs();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return name;
    }
}
