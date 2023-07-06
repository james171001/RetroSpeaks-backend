package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.Auth.RegisterRequest;
import com.champ.retrospeaks.dto.User.UserDto;
import com.champ.retrospeaks.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    //Don't allow to create an instance for utility class
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User registerRequestToUser(RegisterRequest registerRequest,PasswordEncoder passwordEncoder){
        return User.builder()
                .userName(registerRequest.getUserName())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .passWord(passwordEncoder.encode(registerRequest.getPassWord()))
                .gender(registerRequest.getGender())
                .build();

    }

    public static UserDto toUserDto(User user){
        return  UserDto.builder()
                .userName(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }
}
