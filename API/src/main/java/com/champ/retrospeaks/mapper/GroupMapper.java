package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Group.GroupDto;
import com.champ.retrospeaks.dto.Group.GroupForCreationDto;
import com.champ.retrospeaks.dto.User.UserDto;
import com.champ.retrospeaks.model.Group;

import java.util.List;
import java.util.stream.Collectors;

public class GroupMapper {

    private GroupMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static GroupDto toGroupDto(Group group) {
        List<UserDto> userDtos = group.getUsers().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());

        return GroupDto.builder()
                .name(group.getName())
                .description(group.getDescription())
                .createdDate(group.getCreatedDate())
                .updatedDate(group.getUpdatedDate())
                .users(userDtos)
                .build();

    }

   public static Group toGroup(GroupForCreationDto groupForCreationDto){

        return Group.builder()
                .name(groupForCreationDto.getName())
                .description(groupForCreationDto.getDescription())
                .groupOwner(groupForCreationDto.getGroupOwner())
                .build();
    }
}
