package com.project.uber.uberApplication.dto;

import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private long id;

    private Rider rider;

    private DriverDto driver;

    private PointDto pickUpLocation;

    private PointDto dropLocation;

    private LocalDateTime requestedTime;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
