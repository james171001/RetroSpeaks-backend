package com.champ.retrospeaks.service;

import com.champ.retrospeaks.dto.Category.CategoryDTO;
import com.champ.retrospeaks.dto.Group.GroupDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryById(Long id);

    List<GroupDto> getGroupsByCategory(Long id);

    public void saveCategory(CategoryDTO categoryDTO);
}
