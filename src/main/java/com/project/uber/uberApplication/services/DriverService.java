package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;

import java.util.List;

public interface DriverService {

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RiderDto rateRider(Long rideId);

    RideDto acceptRide(Long rideId, Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();

    Driver getCurrentDriver();

    RideDto acceptRide(Long rideRequestId);

}
