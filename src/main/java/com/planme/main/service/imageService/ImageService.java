package com.planme.main.service.imageService;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    public String uploadFile(MultipartFile file, String dirName) throws IOException;
}
