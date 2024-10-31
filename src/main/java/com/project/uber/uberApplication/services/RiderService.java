package com.project.uber.uberApplication.services;

import com.codingshuttle.project.uber.uberApplication.dto.*;
import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RideRequestDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.User;

import java.util.List;

public interface RiderService {

    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);

    RiderDto rateDriver(Long rideId);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();

    Rider createNewRider(User user);
}
