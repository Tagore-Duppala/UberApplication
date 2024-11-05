package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.enums.RideRequestStatus;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import com.project.uber.uberApplication.repositories.DriverRepository;
import com.project.uber.uberApplication.services.DriverService;
import com.project.uber.uberApplication.services.PaymentService;
import com.project.uber.uberApplication.services.RideRequestService;
import com.project.uber.uberApplication.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;
    private final RideRequestService rideRequestService;
    private final PaymentService paymentService;

    @Override
    public RideDto startRide(Long rideId, String otp) {

        Ride findRide = rideService.getRideById(rideId);

        if(!otp.equals(findRide.getOtp())) throw new RuntimeException("OTP is invalid: "+otp);
        if(!findRide.getRideStatus().equals(RideStatus.CONFIRMED)) throw new RuntimeException("Ride status is not confirmed so cannot start the ride: "+findRide.getRideStatus());

        findRide.setStartedAt(LocalDateTime.now());
        paymentService.createPayment(findRide);
        rideService.updateRideStatus(findRide,RideStatus.ONGOING);

       return modelMapper.map(findRide,RideDto.class);
    }

    @Override
    @Transactional
    public RideDto endRide(Long rideId) {

        Ride findRide = rideService.getRideById(rideId);

        if(!findRide.getDriver().equals(getCurrentDriver())) throw new RuntimeException("Driver is not authorized for cancelling the ride");
        if(!findRide.getRideStatus().equals(RideStatus.ONGOING)) throw new RuntimeException("Ride status is not ONGOING so cannot end the ride: "+findRide.getRideStatus());

        Ride savedRide = rideService.updateRideStatus(findRide,RideStatus.ENDED);
        findRide.setEndedAt(LocalDateTime.now());
        updateDriverAvailability(findRide.getDriver(),true);

        paymentService.processPayment(findRide);

        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {

        Ride ride = rideService.getRideById(rideId);
        Driver driver = getCurrentDriver();

        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)) throw new RuntimeException("Cannot cancel the ride as ride has/is : "+ride.getRideStatus());
        if(!ride.getDriver().equals(driver)) throw new RuntimeException(("Driver cannot cancel the ride"));

        rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        updateDriverAvailability(driver,true);
        return  modelMapper.map(ride,RideDto.class);

    }

    @Override
    public RiderDto rateRider(Long rideId) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        Driver driver = getCurrentDriver();
        return modelMapper.map(driver,DriverDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Driver driver = getCurrentDriver();
        return rideService.getAllRidesOfDriver(driver, pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class));

    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(3L).orElseThrow(()-> new RuntimeException("Driver not found with Id: "+2));
    }

    @Override
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.SEARCHING)) throw new RuntimeException("RideRequest cannot be accepted, status is "+ rideRequest.getRideRequestStatus());

        Driver driver = getCurrentDriver();
        if(!driver.getAvailable()) throw new RuntimeException("Driver cannot accept ride due to unavailability");

        Ride newRide = rideService.createNewRide(rideRequest, driver);

        updateDriverAvailability(newRide.getDriver(),false);

        return modelMapper.map(newRide, RideDto.class);

    }

    @Override
    public void updateDriverAvailability(Driver driver, Boolean status) {

        driver.setAvailable(status);
        driverRepository.save(driver);

    }
}
