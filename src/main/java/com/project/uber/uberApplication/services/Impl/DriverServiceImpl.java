package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.enums.RideRequestStatus;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.DriverRepository;
import com.project.uber.uberApplication.repositories.RideRequestRepository;
import com.project.uber.uberApplication.services.DriverService;
import com.project.uber.uberApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RideRequestRepository rideRequestRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long rideId) {
        return null;
    }

    @Override
    public RideDto acceptRide(Long rideId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(3L).orElseThrow(()-> new RuntimeException("Driver not found with Id: "+2));
    }

    @Override
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestRepository.findById(rideRequestId).orElseThrow(()-> new ResourceNotFoundException("RideRequest not found with id: "+rideRequestId));

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.SEARCHING)){
            throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());
        }

        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()){
            throw new RuntimeException("Driver cannot accept ride due to unavailability");
        }

        Ride newRide = rideService.createNewRide(rideRequest, driver);

        return modelMapper.map(newRide, RideDto.class);

    }
}
