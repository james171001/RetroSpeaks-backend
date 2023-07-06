package com.champ.retrospeaks.dto.Group;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GroupForCreationDto {

    private String name;
    private String description;

}
