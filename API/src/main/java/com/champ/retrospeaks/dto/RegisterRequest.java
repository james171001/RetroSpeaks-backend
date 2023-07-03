package com.champ.retrospeaks.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor

public class RegisterRequest {

    private String userName;

    private String passWord;

    private String email;

    private String firstName;

    private String lastName;

    private String gender;
}
