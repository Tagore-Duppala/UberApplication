package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RideRequestDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.services.RideService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {

    @Override
    public RideDto getRideById(Long rideId) {
        return null;
    }

    @Override
    public void matchWithDriver(RideRequestDto rideRequestDto) {

    }

    @Override
    public RideDto createNewRide(RideRequestDto rideRequestDto, Driver driver) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Long riderId, PageRequest pageRequest) {
        return null;
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Long DriverId, PageRequest pageRequest) {
        return null;
    }
}
