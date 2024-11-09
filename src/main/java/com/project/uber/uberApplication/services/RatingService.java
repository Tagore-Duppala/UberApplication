package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Ride;

public interface RatingService {

    DriverDto rateDriver(Ride ride, Double rating);

    RiderDto rateRider(Ride ride, Double rating);

    void createNewRating(Ride ride);

}
