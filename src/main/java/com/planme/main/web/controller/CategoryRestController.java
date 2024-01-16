package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.service.categoryService.CategoryCommandService;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryCommandService categoryCommandService;

    @PostMapping
    public ApiResponse<CategoryResponseDTO.CreateCategoryResultDTO> createCategory(@RequestBody CategoryRequestDTO.CreateCategoryDto request) {

        Category category = categoryCommandService.createCategory(request);

        return ApiResponse.onSuccess(CategoryConverter.toCreateResultDTO(category));
    }
}
