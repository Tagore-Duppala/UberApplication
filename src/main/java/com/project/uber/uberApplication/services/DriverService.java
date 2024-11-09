package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DriverService {

    RideDto startRide(Long rideId, String otp);

    RideDto endRide(Long rideId);

    RideDto cancelRide(Long rideId);

    RiderDto rateRider(Long rideId, Double rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Driver getCurrentDriver();

    RideDto acceptRide(Long rideRequestId);

    void updateDriverAvailability(Driver driver, Boolean status);

}
