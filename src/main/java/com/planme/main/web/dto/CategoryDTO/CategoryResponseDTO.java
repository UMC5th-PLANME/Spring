package com.planme.main.web.dto.CategoryDTO;

import lombok.*;

import java.time.LocalDateTime;

public class CategoryResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCategoryResultDTO {
        Long categoryId;
        LocalDateTime createdAt;
    }

}
