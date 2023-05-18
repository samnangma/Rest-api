package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.model.response.FileResponse;
import com.demo.dataanalyticrestfulapi.service.FileStorageService;
import com.demo.dataanalyticrestfulapi.utils.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/file-service")
public class FileRestController {

    private final List<String> ALLOWED_EXTENSIONS = List.of("jpg","png","jpeg");
    private  final long  MAX_FILE_SIZE = 1024* 1024 * 5 ;
    @Autowired
    FileStorageService fileStorageService;

    @PostMapping("/file-upload")
    public Response<FileResponse> fileUpload(@RequestParam("file") MultipartFile file) throws Exception{
           FileResponse response = uploadFile(file);
           return  Response.<FileResponse>ok().setPayload(response).setMessage("Successfully upload a file ");
    }

    @PostMapping("/multiple-file-upload")
    public Response<List<FileResponse>> uploadMultipleFiles (@RequestParam MultipartFile[] files){

            List<FileResponse> responses= Arrays.stream(files).map(file ->uploadFile(file)).toList();
            return Response.<List<FileResponse>>ok().setPayload(responses).setMessage("Successfully upload mulitple files");


    }

    @DeleteMapping("/delete-file/{filename}")
    public String deleteSingleFile (@PathVariable String filename){
        String result = fileStorageService.deleteFileByName(filename);

        return result;
    }

    @DeleteMapping("/delete-all-files")
    public String deleteAllFiles(){
        String result = fileStorageService.deleteAllFiles();
        return result;
    }

    // for download file handling
    @GetMapping("/download-file/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename , HttpServletRequest request) throws Exception{
        Resource resource = fileStorageService.loadFileAsResource(filename);
        // Try to determine file content
        String contentType = null;
        try{
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        }catch (IOException exception){
            System.out.println("Errors Getting content type is : "+ exception.getMessage());
        }
        if(contentType==null){
            contentType="application/octet-stream";
        }
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }


    private FileResponse uploadFile(MultipartFile file){

        if(file.isEmpty())
            throw new IllegalArgumentException("Files cannot be empty");

        if(file.getSize()> MAX_FILE_SIZE)
            throw new MaxUploadSizeExceededException(MAX_FILE_SIZE);

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        if(!ALLOWED_EXTENSIONS.contains(extension.toLowerCase())){
            throw new IllegalArgumentException("Allowed Extension are 'jpg','jep','png'");
        }

        String filename = fileStorageService.uploadFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file-service/download-file/")
                .path(filename)
                .toUriString();
        return new FileResponse().setFilename(filename).setFileDownloadUri(fileDownloadUri).setFileType(file.getContentType()).setSize(file.getSize());
    }
}
