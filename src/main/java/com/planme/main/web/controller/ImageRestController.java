package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.ImageConverter;
import com.planme.main.service.imageService.ImageService;
import com.planme.main.web.dto.ImageDTO.ImageResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/image")
public class ImageRestController {
    private final ImageService imageService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<ImageResponseDTO.uploadImageResultDTO> uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        String imageUrl = imageService.uploadFile(image, "images");
        return ApiResponse.of(SuccessStatus.IMAGE_FOUND, ImageConverter.toUploadImageResultDTO(imageUrl));
    }

}
