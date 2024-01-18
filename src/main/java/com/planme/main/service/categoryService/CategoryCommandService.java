package com.planme.main.service.categoryService;

import com.planme.main.domain.Category;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;
import com.planme.main.web.dto.CategoryDTO.CategoryResponseDTO;

public interface CategoryCommandService {

    Category createCategory(CategoryRequestDTO.CreateCategoryDto request);

    CategoryResponseDTO.DeleteCategoryResultDTO deleteCategory(Long id);

    Category updateCategory(Long id, CategoryRequestDTO.UpdateCategoryDto request);

    Category changeCategoryStatus(Long id);

}
