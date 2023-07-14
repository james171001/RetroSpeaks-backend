//package com.champ.retrospeaks.controller;
//
//import com.champ.retrospeaks.dto.Category.CategoryDTO;
//import com.champ.retrospeaks.model.Category;
//import com.champ.retrospeaks.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/group/category")
//public class CategoryController {
//    private final CategoryService categoryService;
//
//    @Autowired
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
//        List<CategoryDTO> categories = categoryService.getAllCategories();
//        return ResponseEntity.ok(categories);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
//        CategoryDTO category = categoryService.getCategoryById(id);
//        if (category != null) {
//            return ResponseEntity.ok(category);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @PostMapping
//    public ResponseEntity<String> createCategory() {
//        categoryService.saveCategory();
//
//        return ResponseEntity.ok("Category created successfully.");
//    }
//}
