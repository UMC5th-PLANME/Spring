package com.planme.main.converter;

import com.planme.main.web.dto.ImageDTO.ImageResponseDTO;

public class ImageConverter {
    public static ImageResponseDTO.uploadImageResultDTO toUploadImageResultDTO(String imageURl){
        return ImageResponseDTO.uploadImageResultDTO.builder()
                .imageUrl(imageURl)
                .build();
    }
}
