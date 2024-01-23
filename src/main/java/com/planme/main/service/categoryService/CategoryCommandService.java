package com.planme.main.service.categoryService;

import com.planme.main.domain.Category;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CategoryCommandService {

    Category createCategory(HttpServletRequest httpServletRequest, CategoryRequestDTO.CreateCategoryDto request);

    CategoryResponseDTO.DeleteCategoryResultDTO deleteCategory(HttpServletRequest httpServletRequest, Long id);

    Category updateCategory(HttpServletRequest httpServletRequest, Long id, CategoryRequestDTO.UpdateCategoryDto request);

    Category changeCategoryStatus(HttpServletRequest httpServletRequest, Long id);

}
