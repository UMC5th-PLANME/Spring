package com.planme.main.converter;

import com.planme.main.domain.Category;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;

import java.time.LocalDateTime;

public class CategoryConverter {

    public static CategoryResponseDTO.CreateCategoryResultDTO toCreateResultDTO(Category category) {
        return CategoryResponseDTO.CreateCategoryResultDTO.builder()
                .categoryId(category.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Category toCategory(CategoryRequestDTO.CreateCategoryDto request) {
        return Category.builder()
                .name(request.getName())
                .emoticon(request.getEmoticon())
                .color(request.getColor())
                .build();
    }
}
