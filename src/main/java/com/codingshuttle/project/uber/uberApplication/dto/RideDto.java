package com.codingshuttle.project.uber.uberApplication.dto;

import com.codingshuttle.project.uber.uberApplication.entities.Driver;
import com.codingshuttle.project.uber.uberApplication.entities.Rider;
import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.codingshuttle.project.uber.uberApplication.entities.enums.RideStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

public class RideDto {

    private long id;

    private Rider rider;

    private DriverDto driver;

    private Point pickUpLocation;

    private Point dropLocation;

    private LocalDateTime requestedTime;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
