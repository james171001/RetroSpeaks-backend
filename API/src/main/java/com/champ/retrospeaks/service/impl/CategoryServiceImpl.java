//package com.champ.retrospeaks.service.impl;
//
//import com.champ.retrospeaks.dto.Category.CategoryDTO;
//import com.champ.retrospeaks.mapper.CategoryMapper;
//import com.champ.retrospeaks.model.Category;
//import com.champ.retrospeaks.repository.CategoryRepository;
//import com.champ.retrospeaks.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class CategoryServiceImpl implements CategoryService {
//
//    private final CategoryRepository categoryRepository;
//    private final CategoryMapper categoryMapper;
//
//    @Autowired
//    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
//        this.categoryRepository = categoryRepository;
//        this.categoryMapper = categoryMapper;
//    }
//
//    @Override
//    public List<CategoryDTO> getAllCategories() {
//        List<Category> categories = categoryRepository.findAll();
//        return categories.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    private CategoryDTO convertToDTO(Category category) {
//        CategoryDTO categoryDTO = new CategoryDTO();
//        categoryDTO.setId(category.getId());
////        categoryDTO.setCategoryId(category.getCategoryId());
//        return categoryDTO;
//    }
//    @Override
//    public CategoryDTO getCategoryById(Long id) {
//        Category category = categoryRepository.findById((long) id).orElse(null);
//        if (category != null) {
//            return categoryMapper.toDto(category);
//        }
//        return null;
//    }
//
//    @Override
//    public void saveCategory() {
//        Category category = new Category();
//        category.setCategoryType(Category.CategoryType.valueOf(Category.CategoryType.ENTERTAINMENT.toString()));
//    }
//}
