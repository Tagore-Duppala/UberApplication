package com.project.uber.uberApplication.repositories;

import com.project.uber.uberApplication.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
}
