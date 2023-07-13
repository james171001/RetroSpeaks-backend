package com.champ.retrospeaks.dto.User;


import com.champ.retrospeaks.repository.UserRepository;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDto {

    private String userName;

    private String email;

    private String firstName;

    private String lastName;

}

