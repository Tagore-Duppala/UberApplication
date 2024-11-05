package com.project.uber.uberApplication.repositories;

import com.project.uber.uberApplication.entities.Payment;
import com.project.uber.uberApplication.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Payment findByRide(Ride ride);
}
