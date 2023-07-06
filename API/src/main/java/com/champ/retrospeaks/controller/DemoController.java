package com.champ.retrospeaks.controller;


import com.champ.retrospeaks.dto.Auth.AuthRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {



    @GetMapping
    public ResponseEntity<AuthRequestDto> demo(){
        AuthRequestDto authRequestDto = new AuthRequestDto("12312","2312");
        return ResponseEntity.ok(authRequestDto);
    }
}
