package com.odss.seu.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadPictureService {
    String upload(MultipartFile file);
}
