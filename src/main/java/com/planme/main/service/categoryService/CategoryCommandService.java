package com.planme.main.service.categoryService;

import com.planme.main.domain.Category;
import com.planme.main.web.dto.CategoryDTO.CategoryRequestDTO;

public interface CategoryCommandService {

    Category createCategory(CategoryRequestDTO.CreateCategoryDto request);
}
