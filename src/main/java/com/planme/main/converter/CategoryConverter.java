package com.planme.main.converter;

import com.planme.main.domain.Category;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter {

    public static CategoryResponseDTO.CreateCategoryResultDTO toCreateResultDTO(Category category) {
        return CategoryResponseDTO.CreateCategoryResultDTO.builder()
                .categoryId(category.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }



    public static CategoryResponseDTO.DeleteCategoryResultDTO toDeleteResultDTO(Category category) {
        return CategoryResponseDTO.DeleteCategoryResultDTO.builder()
                .categoryId(category.getId())
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public static CategoryResponseDTO.UpdateCategoryResultDTO toUpdateResultDTO(Category category) {
        return CategoryResponseDTO.UpdateCategoryResultDTO.builder()
                .categoryId(category.getId())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static CategoryResponseDTO.ChangeStatusCategoryResultDTO toChangeStatusResultDTO(Category category) {
        return CategoryResponseDTO.ChangeStatusCategoryResultDTO.builder()
                .categoryId(category.getId())
                .meStoryHidden(category.isMeStoryHidden())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Category toCategory(CategoryRequestDTO.CreateCategoryDto request) {
        return Category.builder()
                .name(request.getName())
                .emoticon(request.getEmoticon())
                .color(request.getColor())
                .build();
    }

    public static CategoryResponseDTO.GetCategoryResultDTO toGetCategoryResultDTO(Category category){
        return CategoryResponseDTO.GetCategoryResultDTO.builder()
                .categoryId(category.getId())
                .name(category.getName())
                .emoticon(category.getEmoticon())
                .color(category.getColor())
                .meStoryHidden(category.isMeStoryHidden())
                .createdAt(category.getCreatedAt())
                .updatedAt(category.getUpdatedAt())
                .build();
    }

    public static CategoryResponseDTO.GetCategoryListResultDTO toGetCategoryListDTO(List<Category> categoryList) {
        List<CategoryResponseDTO.GetCategoryResultDTO> categoryResultDTOList = categoryList.stream()
                .map(CategoryConverter::toGetCategoryResultDTO).collect(Collectors.toList());
        return CategoryResponseDTO.GetCategoryListResultDTO.builder()
                .categoryList(categoryResultDTOList).build();
    }

}
