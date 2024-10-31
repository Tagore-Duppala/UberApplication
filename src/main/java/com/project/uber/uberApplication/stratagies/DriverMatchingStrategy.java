package com.project.uber.uberApplication.stratagies;

import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {
    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
