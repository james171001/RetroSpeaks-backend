package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.dto.User.UserDto;
import com.champ.retrospeaks.model.Category;
import com.champ.retrospeaks.model.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {
    private GroupMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static GroupDto toGroupDto(Group group) {
        GroupDto.GroupDtoBuilder builder = GroupDto.builder()
                .id(group.getId())
                .name(group.getName())
                .description(group.getDescription())
                .createdDate(group.getCreatedDate())
                .updatedDate(group.getUpdatedDate())
                .category(CategoryMapper.toDto(group.getCategory()));

        if (group.getUsers() != null) {
            List<UserDto> userDtos = group.getUsers().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
            builder.users(userDtos);
        } else {
            builder.users(Collections.emptyList());
        }

        return builder.build();
    }


    public static Group toGroup(GroupForCreationDto groupForCreationDto, Category category) {
        return Group.builder()
                .name(groupForCreationDto.getName())
                .description(groupForCreationDto.getDescription())
                .category(category)
                .users(new ArrayList<>())
                .build();
    }

}
