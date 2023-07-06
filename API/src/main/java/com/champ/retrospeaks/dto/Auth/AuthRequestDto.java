package com.champ.retrospeaks.dto.Auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequestDto {

   private String userName;
   private String passWord;
}
