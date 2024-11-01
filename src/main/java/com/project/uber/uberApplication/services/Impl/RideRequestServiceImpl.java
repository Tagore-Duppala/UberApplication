package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.entities.RideRequest;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.RideRequestRepository;
import com.project.uber.uberApplication.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {

    private final RideRequestRepository rideRequestRepository;

    @Override
    public void updateRideRequest(RideRequest rideRequest) {
        rideRequestRepository.findById(rideRequest.getId())
                .orElseThrow(() -> new ResourceNotFoundException("RideRequestId not found with id: "+ rideRequest.getId())
                );
        rideRequestRepository.save(rideRequest);


    }
}
