//package com.champ.retrospeaks.mapper;
//
//import com.champ.retrospeaks.dto.Category.CategoryDTO;
//import com.champ.retrospeaks.model.Category;
//import org.springframework.stereotype.Component;
//
//@Component
//public class CategoryMapper {
//    public CategoryDTO toDto(Category category) {
//        return CategoryDTO.builder()
//                .id(category.getId())
////                .categoryId(category.getCategoryId())
//                .categoryType((category.getCategoryType().name()))
//                .build();
//    }
//
//    public Category toEntity(CategoryDTO categoryDTO) {
//        return Category.builder()
//                .id(categoryDTO.getId())
////                .categoryId(categoryDTO.getCategoryId())
//                .categoryType(Category.CategoryType.valueOf(categoryDTO.getCategoryType()))
//                .build();
//    }
//
//}
