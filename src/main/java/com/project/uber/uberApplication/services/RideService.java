package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.RideRequestDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    void matchWithDriver(RideRequestDto rideRequestDto);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Long DriverId, PageRequest pageRequest);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
}
