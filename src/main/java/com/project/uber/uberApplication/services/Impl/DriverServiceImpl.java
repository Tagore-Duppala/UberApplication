package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.enums.RideRequestStatus;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.DriverRepository;
import com.project.uber.uberApplication.repositories.RideRepository;
import com.project.uber.uberApplication.repositories.RideRequestRepository;
import com.project.uber.uberApplication.services.DriverService;
import com.project.uber.uberApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RideRequestRepository rideRequestRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final RideRepository rideRepository;

    @Override
    public RideDto startRide(Long rideId, String otp) {

        Ride findRide = rideService.getRideById(rideId);

        if(!otp.equals(findRide.getOtp())) throw new RuntimeException("OTP is invalid: "+otp);
        if(!findRide.getRideStatus().equals(RideStatus.CONFIRMED)) throw new RuntimeException("Ride status is not confirmed so cannot start the ride: "+findRide.getRideStatus());

        findRide.setStartedAt(LocalDateTime.now());
        rideService.updateRideStatus(findRide,RideStatus.ONGOING);

       return modelMapper.map(findRide,RideDto.class);
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

        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.SEARCHING)) throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());

        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()) throw new RuntimeException("Driver cannot accept ride due to unavailability");

        Ride newRide = rideService.createNewRide(rideRequest, driver);

        driver.setAvailable(false);
        driverRepository.save(driver);

        return modelMapper.map(newRide, RideDto.class);

    }
}
