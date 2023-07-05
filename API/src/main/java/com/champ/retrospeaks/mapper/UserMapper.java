package com.champ.retrospeaks.mapper;

import com.champ.retrospeaks.dto.RegisterRequest;
import com.champ.retrospeaks.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    //Don't allow to create an instance for utility class
    private UserMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static User registerRequestToUser(RegisterRequest registerRequest,PasswordEncoder passwordEncoder){
        return User.builder()
                .firstName(registerRequest.getFirstName())
                .userName(registerRequest.getUserName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .passWord(passwordEncoder.encode(registerRequest.getPassWord()))
                .gender(registerRequest.getGender())
                .build();

    }
}
