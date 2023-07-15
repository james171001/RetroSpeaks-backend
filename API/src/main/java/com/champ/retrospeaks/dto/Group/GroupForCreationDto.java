package com.champ.retrospeaks.dto.Group;


import com.champ.retrospeaks.dto.Category.CategoryDTO;
import com.champ.retrospeaks.model.Group;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupForCreationDto {

    private String name;
    private String description;
    private Long categoryId;
}
