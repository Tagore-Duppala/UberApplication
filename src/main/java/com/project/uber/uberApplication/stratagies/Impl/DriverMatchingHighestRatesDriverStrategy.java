package com.project.uber.uberApplication.stratagies.Impl;

import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.repositories.DriverRepository;
import com.project.uber.uberApplication.stratagies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingHighestRatesDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {

        return driverRepository.findNearByTopRatedDrivers(rideRequest.getPickUpLocation());
    }
}
