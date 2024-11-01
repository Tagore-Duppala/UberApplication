package com.project.uber.uberApplication.dto;

import com.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.project.uber.uberApplication.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideRequestDto {

    private Long id;

    private RiderDto rider;

    private PointDto pickUpLocation;

    private PointDto dropLocation;

    private LocalDateTime requestedTime;

    private Double fare;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;


}
