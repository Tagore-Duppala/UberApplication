package com.project.uber.uberApplication.controllers;

import com.project.uber.uberApplication.dto.SignUpDto;
import com.project.uber.uberApplication.dto.UserDto;
import com.project.uber.uberApplication.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthContorller {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signup(signUpDto);
    }


}
