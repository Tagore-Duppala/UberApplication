package com.project.uber.uberApplication.stratagies.Impl;

import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.services.DistanceService;
import com.project.uber.uberApplication.stratagies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class RideFareDefaultFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropLocation());

        return distance*RIDE_FARE_MULTIPLIER;
    }
}
