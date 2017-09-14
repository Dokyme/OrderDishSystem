package com.odss.seu.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadPictureServiceImpl implements UploadPictureService {

    private static final String relativePath = "/image/";
    private static final String abosolutePath = "C:\\Program Files\\Apache24\\htdocs\\image\\";

    @Override
    public String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filename = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
                file.transferTo(new File(abosolutePath + filename));
                System.out.println(abosolutePath + filename);
                return relativePath + filename;
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
