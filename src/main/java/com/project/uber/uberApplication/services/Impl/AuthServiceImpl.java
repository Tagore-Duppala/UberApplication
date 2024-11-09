package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.SignUpDto;
import com.project.uber.uberApplication.dto.UserDto;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.enums.Role;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.UserRepository;
import com.project.uber.uberApplication.services.AuthService;
import com.project.uber.uberApplication.services.RiderService;
import com.project.uber.uberApplication.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;

    @Override
    public void login(String email, String password) {

    }

    @Override
    @Transactional
    public UserDto signup(SignUpDto signUpDto) {
         User user1 = userRepository.findByEmail(signUpDto.getEmail()).orElse(null);
         if(user1!=null) throw new
                 ResourceNotFoundException("User already exist with email "+ signUpDto.getEmail());

        User user = modelMapper.map(signUpDto,User.class);
        user.setRoles(Set.of(Role.RIDER));

        User savedUser = userRepository.save(user); //saving details in user entity
        Rider rider=riderService.createNewRider(user); //create new rider, saving in rider entity

        walletService.createNewWallet(savedUser);

        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long UserId, String vehicleId){
        return null;
    }
}
