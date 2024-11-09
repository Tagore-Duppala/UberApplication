package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Rating;
import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.exceptions.RuntimeConflictException;
import com.project.uber.uberApplication.repositories.DriverRepository;
import com.project.uber.uberApplication.repositories.RatingRepository;
import com.project.uber.uberApplication.repositories.RiderRepository;
import com.project.uber.uberApplication.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;
    private final RiderRepository riderRepository;

    @Override
    public DriverDto rateDriver(Ride ride, Double rating) {
        Rating rating1 = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found for ride with Id: "+ride.getId()));
        if(rating1.getDriverRating()!=null) throw new RuntimeConflictException("Driver was already rated for this ride, Cannot rate again");

        rating1.setDriverRating(rating);
        ratingRepository.save(rating1); // setting rating for the particular ride

        Double newRatingAvg = ratingRepository.findByDriver(ride.getDriver())
                .stream()
                .mapToDouble(rating2 -> rating2.getDriverRating())
                .average()
                .orElse(0.0);
        ride.getDriver().setRating(newRatingAvg); //setting rating avg for the particular driver

        Driver savedDriver =  driverRepository.save(ride.getDriver());
        return modelMapper.map(savedDriver, DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Double rating) {
        Rating rating1 = ratingRepository.findByRide(ride)
                .orElseThrow(()-> new ResourceNotFoundException("Rating not found for ride with Id: "+ride.getId()));
        if(rating1.getRiderRating()!=null) throw new RuntimeConflictException("Rider was already rated for this ride, Cannot rate again");

        rating1.setRiderRating(rating);
        ratingRepository.save(rating1); // setting rating for the particular ride

        Double newRatingAvg = ratingRepository.findByRider(ride.getRider())
                .stream()
                .mapToDouble(rating2 -> rating2.getRiderRating())
                .average()
                .orElse(0.0);
        ride.getRider().setRating(newRatingAvg); //setting rating avg for the particular driver

        Rider savedRider =  riderRepository.save(ride.getRider());
        return modelMapper.map(savedRider, RiderDto.class);
    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating=  Rating.builder()
                .driver(ride.getDriver())
                .rider(ride.getRider())
                .build();
        ratingRepository.save(rating);

    }
}
