package com.champ.retrospeaks.service.impl;

import com.champ.retrospeaks.dto.Category.CategoryDTO;
import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.mapper.CategoryMapper;
import com.champ.retrospeaks.mapper.GroupMapper;
import com.champ.retrospeaks.model.Category;
import com.champ.retrospeaks.model.Group;
import com.champ.retrospeaks.repository.CategoryRepository;
import com.champ.retrospeaks.repository.GroupRepository;
import com.champ.retrospeaks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final GroupRepository groupRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, GroupRepository groupRepository) {
        this.categoryRepository = categoryRepository;

        this.groupRepository = groupRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById((long) id).orElseThrow( ()-> new IllegalStateException("Invalid Id"));
        if (category != null) {
            return CategoryMapper.toDto(category);
        }
        return null;
    }

    @Override
    public List<GroupDto> getGroupsByCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Category ID"));

        List<Group> groups = groupRepository.findByCategory(category);
        return groups.stream()
                .map(GroupMapper::toGroupDto)
                .collect(Collectors.toList());
    }
    @Override
    public void saveCategory(CategoryDTO categoryDTO) {

        categoryRepository.save(CategoryMapper.toEntity(categoryDTO));


    }
}
