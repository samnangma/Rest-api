package com.demo.dataanalyticrestfulapi.controller;

import com.demo.dataanalyticrestfulapi.model.response.FileResponse;
import com.demo.dataanalyticrestfulapi.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileUploadController {
    private final FileService fileService;

     FileUploadController(FileService fileService){
         this.fileService = fileService;
     }

     @Value("${Upload.image}")
     private String path;

     @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image){
         String Filename = null;
         try{
             Filename = fileService.UploadImage(path,image);

         } catch (IOException e) {
             return new ResponseEntity<>(new FileResponse(null,"Images upload UnSuccessfully !! Please try again"), HttpStatus.OK);
         }
         return new ResponseEntity<>(new FileResponse(Filename,"Images upload is successfully"),HttpStatus.OK);
     }




}
