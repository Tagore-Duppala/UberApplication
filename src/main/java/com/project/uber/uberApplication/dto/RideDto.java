package com.project.uber.uberApplication.dto;

import com.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.project.uber.uberApplication.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {

    private long id;


    private RiderDto rider;

    private DriverDto driver;

    private PointDto pickUpLocation;

    private PointDto dropLocation;

    private LocalDateTime requestedTime;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private String otp;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

}
