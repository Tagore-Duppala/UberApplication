package com.project.uber.uberApplication.controllers;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.OnboardNewDriverDto;
import com.project.uber.uberApplication.dto.SignUpDto;
import com.project.uber.uberApplication.dto.UserDto;
import com.project.uber.uberApplication.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthContorller {

    private final AuthService authService;

    @PostMapping(path = "/signup")
    public UserDto signUp(@RequestBody SignUpDto signUpDto){
        return authService.signup(signUpDto);
    }

    @PostMapping("/onboardNewDriver/{userId}")
    public ResponseEntity<DriverDto> onboardNewDriver(@PathVariable Long userId, @RequestBody OnboardNewDriverDto onboardNewDriverDto){
        return ResponseEntity.ok(authService.onboardNewDriver(userId,onboardNewDriverDto.getVehicleId()));
    }

}
