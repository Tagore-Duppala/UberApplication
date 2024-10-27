package com.codingshuttle.project.uber.uberApplication.entities;

import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.codingshuttle.project.uber.uberApplication.entities.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double amount;

    @CreationTimestamp
    private LocalDateTime paymentTime;
}
