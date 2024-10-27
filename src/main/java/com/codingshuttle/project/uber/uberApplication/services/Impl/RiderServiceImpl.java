package com.codingshuttle.project.uber.uberApplication.services.Impl;

import com.codingshuttle.project.uber.uberApplication.dto.DriverDto;
import com.codingshuttle.project.uber.uberApplication.dto.RideDto;
import com.codingshuttle.project.uber.uberApplication.dto.RideRequestDto;
import com.codingshuttle.project.uber.uberApplication.dto.RiderDto;
import com.codingshuttle.project.uber.uberApplication.entities.RideRequest;
import com.codingshuttle.project.uber.uberApplication.entities.Rider;
import com.codingshuttle.project.uber.uberApplication.entities.User;
import com.codingshuttle.project.uber.uberApplication.entities.enums.RideRequestStatus;
import com.codingshuttle.project.uber.uberApplication.repositories.RideRequestRepository;
import com.codingshuttle.project.uber.uberApplication.repositories.RiderRepository;
import com.codingshuttle.project.uber.uberApplication.services.RiderService;
import com.codingshuttle.project.uber.uberApplication.stratagies.DriverMatchingStrategy;
import com.codingshuttle.project.uber.uberApplication.stratagies.RideFareCalculationStrategy;
import com.codingshuttle.project.uber.uberApplication.stratagies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j //to get the logs
public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideStrategyManager rideStrategyManager;

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class); //for converting PointDto in riderrqstDto to Point in RideRequestEnitiy we define a typematch in mapperconfig
        rideRequest.setRideRequestStatus(RideRequestStatus.SEARCHING);
        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);

        rideRequest.setFare(fare);

        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);

        rideStrategyManager.driverMatchingStrategy(2.3).findMatchingDriver(savedRideRequest);

        return modelMapper.map(savedRideRequest,RideRequestDto.class);
    }






    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateDriver(Long rideId) {
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
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }
}
