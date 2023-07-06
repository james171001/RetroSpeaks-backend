package com.champ.retrospeaks.dto.Group;

import com.champ.retrospeaks.dto.User.UserDto;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class GroupDto {

    private String name;
    private String description;

    private LocalDate createdDate;


    private LocalDate updatedDate;

    List<UserDto> users;


}
