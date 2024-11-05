package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.entities.Payment;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);

    Payment createPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
