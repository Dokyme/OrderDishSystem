package com.odss.seu.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadPictureServiceImpl implements UploadPictureService {
    public static Logger lo= Logger.getLogger(UploadPictureServiceImpl.class);

    private static final String relativePath = "/image/";
    private static final String abosolutePath = "C:\\Program Files\\Apache24\\htdocs\\image\\";

    @Override
    public String upload(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filename = UUID.randomUUID().toString().replaceAll("-", "") + file.getOriginalFilename();
                file.transferTo(new File(abosolutePath + filename));
                System.out.println(abosolutePath + filename);
                lo.info("上传图片成功");
                return relativePath + filename;
            } catch (IOException exception) {
                lo.info("上传图片失败");
                exception.printStackTrace();
            }
        }
        return null;
    }
}
