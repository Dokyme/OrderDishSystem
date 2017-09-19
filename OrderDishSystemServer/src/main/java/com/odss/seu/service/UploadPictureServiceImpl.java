package com.odss.seu.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class UploadPictureServiceImpl implements UploadPictureService {
    public static Logger lo = Logger.getLogger(UploadPictureServiceImpl.class);

    private String relativePath;
    private String imageDirPath;

    public UploadPictureServiceImpl(String webRootPath) {
        this.imageDirPath = webRootPath + "\\image\\";
        this.relativePath = "/image/";
    }

    @Override
    public String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filename = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
                file.transferTo(new File(imageDirPath + filename));
                lo.info("上传图片成功" + imageDirPath + filename);
                System.out.println(imageDirPath + filename);
                System.out.println(relativePath + filename);
                return relativePath + filename;
            } catch (IOException exception) {
                lo.info("上传图片失败");
                lo.error(exception.getStackTrace());
            }
        }
        return null;
    }
}
