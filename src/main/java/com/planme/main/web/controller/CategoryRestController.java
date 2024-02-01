package com.planme.main.web.controller;

import com.planme.main.apiPayload.ApiResponse;
import com.planme.main.apiPayload.code.status.SuccessStatus;
import com.planme.main.converter.CategoryConverter;
import com.planme.main.domain.Category;
import com.planme.main.service.categoryService.CategoryCommandService;
import com.planme.main.service.categoryService.CategoryQueryService;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryCommandService categoryCommandService;
    private final CategoryQueryService categoryQueryService;

    /**
     * @param request
     * @return
     */
    @PostMapping
    public ApiResponse<CategoryResponseDTO.CreateCategoryResultDTO> createCategory(HttpServletRequest httpServletRequest, @RequestBody CategoryRequestDTO.CreateCategoryDto request) {

        Category category = categoryCommandService.createCategory(httpServletRequest, request);

        return ApiResponse.of(SuccessStatus.CATEGORY_CREATED, CategoryConverter.toCreateResultDTO(category));
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{categoryId}")
    public ApiResponse<CategoryResponseDTO.DeleteCategoryResultDTO> deleteCategory(HttpServletRequest httpServletRequest, @PathVariable(name = "categoryId") Long id) {
        return ApiResponse.of(SuccessStatus.CATEGORY_DELETED, categoryCommandService.deleteCategory(httpServletRequest, id));
    }

    @PatchMapping("/{categoryId}")
    public ApiResponse<CategoryResponseDTO.UpdateCategoryResultDTO> updateCategory(HttpServletRequest httpServletRequest, @PathVariable(name = "categoryId") Long id, @RequestBody CategoryRequestDTO.UpdateCategoryDto request) {
        Category category = categoryCommandService.updateCategory(httpServletRequest,id, request);
        return ApiResponse.of(SuccessStatus.CATEGORY_UPDATED, CategoryConverter.toUpdateResultDTO(category));
    }

    @PatchMapping("/status/{categoryId}")
    public ApiResponse<CategoryResponseDTO.ChangeStatusCategoryResultDTO> changeStatus(HttpServletRequest httpServletRequest, @PathVariable(name = "categoryId") Long id){
        Category category = categoryCommandService.changeCategoryStatus(httpServletRequest,id);
        return ApiResponse.of(SuccessStatus.CATEGORY_STATUS_CHANGED, CategoryConverter.toChangeStatusResultDTO(category));
    }

    @GetMapping("/{categoryId}")
    public ApiResponse<CategoryResponseDTO.GetCategoryResultDTO> getCategory(HttpServletRequest  httpServletRequest, @PathVariable(name = "categoryId") Long id) {
        Category category = categoryQueryService.getCategory(httpServletRequest, id);
        return ApiResponse.of(SuccessStatus.CATEGORY_FOUND, CategoryConverter.toGetCategoryResultDTO(category));

    }

    @GetMapping("/all")
    public ApiResponse<CategoryResponseDTO.GetCategoryListResultDTO> getCategoryList(HttpServletRequest httpServletRequest) {
        List<Category> categoryList = categoryQueryService.getCategoryList(httpServletRequest);
        return ApiResponse.of(SuccessStatus.CATEGORY_FOUND, CategoryConverter.toGetCategoryListDTO(categoryList));
    }
}
