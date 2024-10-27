package com.codingshuttle.project.uber.uberApplication.stratagies;

import com.codingshuttle.project.uber.uberApplication.dto.RideRequestDto;
import com.codingshuttle.project.uber.uberApplication.entities.RideRequest;

public interface RideFareCalculationStrategy {

    double RIDE_FARE_MULTIPLIER = 10;
    double SURGE_PRICE = 2;
    double calculateFare(RideRequest rideRequest);
}
