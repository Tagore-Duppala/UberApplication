package com.codingshuttle.project.uber.uberApplication.controllers;

import com.codingshuttle.project.uber.uberApplication.dto.RideDto;
import com.codingshuttle.project.uber.uberApplication.dto.SignUpDto;
import com.codingshuttle.project.uber.uberApplication.dto.UserDto;
import com.codingshuttle.project.uber.uberApplication.services.AuthService;
import com.codingshuttle.project.uber.uberApplication.services.DistanceService;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

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
