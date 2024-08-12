package com.lcwd.electronic.store.services.impl;

import com.lcwd.electronic.store.exceptions.BadApiRequest;
import com.lcwd.electronic.store.services.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {
        // abc.png add into filename
        String originalFilename = file.getOriginalFilename();
        // all the time file name generate new
        logger.info("Filename : {}",originalFilename);
        String filename= UUID.randomUUID().toString();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        //add extendion to that file name (filename+extension)
        String fileNameWithExtension=filename+extension;
        String fullPathWithFileName=path+ File.separator+fileNameWithExtension; // full path
        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg")) {
            //file save, first we will create folder if folder not present
            File folder = new File(path);
            if(!folder.exists()){
                //create folder
                folder.mkdirs();
                // upload file
                Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));
                // return the file
                return fileNameWithExtension;
            }
        }else{
            throw new BadApiRequest("File with this"+extension+"not allowed !!");
        }
        return null;
    }

    @Override
    public InputStream getResource(String path, String name) throws FileNotFoundException {
        String fullPath = path+File.separator+name;
        InputStream inputStream= new FileInputStream(fullPath);
        return inputStream;
    }
}