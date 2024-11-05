package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);

    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver Driver, PageRequest pageRequest);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);
}
