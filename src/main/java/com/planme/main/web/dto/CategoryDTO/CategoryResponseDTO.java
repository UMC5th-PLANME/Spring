package com.planme.main.web.dto.CategoryDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public class CategoryResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCategoryResultDTO {
        Long categoryId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteCategoryResultDTO {
        Long categoryId;
        LocalDateTime deletedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateCategoryResultDTO {
        Long categoryId;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeStatusCategoryResultDTO {
        Long categoryId;
        boolean meStoryHidden;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCategoryResultDTO {
        Long categoryId;
        String name;
        String emoticon;
        String color;
        boolean meStoryHidden;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GetCategoryListResultDTO {
        List<GetCategoryResultDTO> categoryList;
    }

}
