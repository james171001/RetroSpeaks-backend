package com.champ.retrospeaks.dto.Group;

import com.champ.retrospeaks.dto.User.UserDto;
import com.champ.retrospeaks.model.Group;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
public class GroupDto {

    private Long id;

    private String name;
    private String description;

    private LocalDate createdDate;


    private LocalDate updatedDate;


    private List<UserDto> users = new ArrayList<>();
    private Group.CategoryType categoryType;


}
