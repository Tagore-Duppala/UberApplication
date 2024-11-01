package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RideRequestDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.enums.RideRequestStatus;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.RideRequestRepository;
import com.project.uber.uberApplication.repositories.RiderRepository;
import com.project.uber.uberApplication.services.RiderService;
import com.project.uber.uberApplication.stratagies.RideStrategyManager;
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
        Rider rider  = getCurrentRider();
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class); //for converting PointDto in riderrqstDto to Point in RideRequestEnitiy we define a typematch in mapperconfig
//        log.info("Ride request ----------> ",rideRequest);
        rideRequest.setRideRequestStatus(RideRequestStatus.SEARCHING);

        Double fare = rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);

        rideRequest.setRider(rider);
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

    @Override
    public Rider getCurrentRider() {
        //TODO: Implement Spring Security

        return riderRepository.findById(1L).orElseThrow(()->
                new ResourceNotFoundException("Rider not found with id: "+1));
    }
}
