package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.SignUpDto;
import com.project.uber.uberApplication.dto.UserDto;

public interface AuthService {

    void login(String email, String password);

    UserDto signup(SignUpDto signUpDto);

    DriverDto onboardNewDriver(Long UserId);



}
