package com.demo.dataanalyticrestfulapi.service.serviceImpl;

import com.demo.dataanalyticrestfulapi.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class FIleStorageServiceImpl implements FileStorageService {
    private final String severLocation = "src/main/resources/images";
    Path fileLocationStorage;

    public FIleStorageServiceImpl(){
        fileLocationStorage = Paths.get(severLocation);
        try{
            if(!Files.exists(fileLocationStorage)){
                Files.createDirectories(fileLocationStorage);
            } else {
                System.out.println("Directory is already existed ");
            }
        } catch (Exception ex){
            System.out.println("Erorr creating directory: " +ex.getMessage());
        }

    }
    @Override
    public String uploadFile(MultipartFile file)  {
        // String[]
        // [0] = cute-image
        // [1] = png
        String filename = file.getOriginalFilename();
        String[] fileCompartments = filename.split("\\.");
        filename = UUID.randomUUID()+"."+fileCompartments[1];
        Path resolovedPath = fileLocationStorage.resolve(filename);

        try{
            Files.copy(file.getInputStream(),resolovedPath,StandardCopyOption.REPLACE_EXISTING);
            return filename;
        } catch (Exception ex){
            return ex.getMessage();
        }
    }

    @Override
    public String deleteFileByName(String filename) {
        Path imagesLocation = Paths.get(severLocation);
        List<File> allFiles =List.of(imagesLocation.toFile().listFiles()) ;

        // filter file that we going to delete
        File deletedFile = allFiles.stream().filter(
                file -> file.getName().equals(filename)
        ).findFirst().orElse(null);

        try {
            if(deletedFile != null){
                Files.delete(deletedFile.toPath());
                return "delete file successfully";
            } else {
                return "file with "+filename +"doesn't exit ";
            }
        }
        catch(Exception ex){
            System.out.println("Error delete file by name : " +ex.getMessage());
            return "failed to delete file by name";
        }

    }

    @Override
    public String deleteAllFiles() {

        Path imageLocation = Paths.get(severLocation);
        File[] files = imageLocation.toFile().listFiles();

        try {
            if(files == null || files.length == 0 ) {
                return "there is no files to delete !!";
            }
            for(File file : files) {
                Files.delete(file.toPath());
            }
            return "Successfully delete all files";
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception Delete all files"+ex.getMessage());
            return "Failed to delete all files ! Execption occurred !";
        }
    }

    @Override
    public Resource loadFileAsResource(String filename) throws Exception {
        Path resourcePath = this.fileLocationStorage.resolve(filename).normalize();
        try {
            Resource resource = new UrlResource(resourcePath.toUri());
            if(resource.exists()){
                return resource;
            } else {
                throw new Exception("Resource doesn't exist ! ");
            }
        } catch (Exception ex){
            throw new Exception("Exception Occurred !! Failed to download the image ");
        }

    }
}
