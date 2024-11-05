package com.project.uber.uberApplication.stratagies;

import com.project.uber.uberApplication.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_FEE = 0.3;

    void processPayment(Payment payment);

}
