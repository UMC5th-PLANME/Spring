package com.planme.main.service.categoryService;

import com.planme.main.domain.Category;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CategoryQueryService{

    Category getCategory(HttpServletRequest httpServletRequest, Long id);

    List<Category> getCategoryList(HttpServletRequest httpServletRequest);
}
