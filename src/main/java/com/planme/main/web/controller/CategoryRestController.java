package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.service.categoryService.CategoryCommandService;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryCommandService categoryCommandService;

    /**
     * TODO: 로그인 API 구현 완료시 HttpServletRequest parameter 추가
     * @param request
     * @return
     */
    @PostMapping
    public ApiResponse<CategoryResponseDTO.CreateCategoryResultDTO> createCategory(@RequestBody CategoryRequestDTO.CreateCategoryDto request) {

        Category category = categoryCommandService.createCategory(request);

        return ApiResponse.of(SuccessStatus.CATEGORY_CREATED,CategoryConverter.toCreateResultDTO(category));
    }

    /**
     * TODO: 로그인 API 구현 완료시 HttpServletRequest parameter 추가
     * @param id
     * @return
     */
    @DeleteMapping("/{categoryId}")
    public ApiResponse<CategoryResponseDTO.DeleteCategoryResultDTO> deleteCategory(@PathVariable(name = "categoryId") Long id){
        return ApiResponse.of(SuccessStatus.CATEGORY_DELETED, categoryCommandService.deleteCategory(id));
    }
}
