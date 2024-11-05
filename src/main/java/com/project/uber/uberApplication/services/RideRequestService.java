package com.project.uber.uberApplication.services;


import com.project.uber.uberApplication.entities.RideRequest;

public interface RideRequestService {

    void updateRideRequest(RideRequest rideRequest);

    RideRequest findRideRequestById(Long rideRequestId);
}
