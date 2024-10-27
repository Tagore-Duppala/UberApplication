package com.codingshuttle.project.uber.uberApplication.stratagies.Impl;

import com.codingshuttle.project.uber.uberApplication.dto.RideRequestDto;
import com.codingshuttle.project.uber.uberApplication.entities.RideRequest;
import com.codingshuttle.project.uber.uberApplication.services.DistanceService;
import com.codingshuttle.project.uber.uberApplication.stratagies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance= distanceService.calculateDistance(rideRequest.getPickUpLocation(),rideRequest.getDropLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_PRICE;
    }
}
