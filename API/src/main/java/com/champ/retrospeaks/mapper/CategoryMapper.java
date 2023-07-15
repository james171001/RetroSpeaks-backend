package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Category.CategoryDTO;
import com.champ.retrospeaks.model.Category;


public class CategoryMapper {

    private CategoryMapper(){
        throw new IllegalStateException("Utility Class");
    }
    public static CategoryDTO toDto(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name((category.getName()))
                .build();
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .name(categoryDTO.getName())
                .build();
    }

}
