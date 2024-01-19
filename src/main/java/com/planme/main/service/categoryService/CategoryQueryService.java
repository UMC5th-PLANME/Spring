package com.planme.main.service.categoryService;

import com.planme.main.domain.Category;

import java.util.List;

public interface CategoryQueryService{

    Category getCategory(Long id);

    List<Category> getCategoryList();
}
