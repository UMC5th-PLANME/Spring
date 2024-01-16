package com.planme.main.web.dto.CategoryDTO;

import lombok.Getter;

public class CategoryRequestDTO {

    @Getter
    public static class CreateCategoryDto {

        private String name;
        private String emoticon;
        private String color;

    }

}
