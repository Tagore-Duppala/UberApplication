package com.codingshuttle.project.uber.uberApplication.stratagies;

import com.codingshuttle.project.uber.uberApplication.entities.Rider;
import com.codingshuttle.project.uber.uberApplication.stratagies.Impl.DriverMatchingHighestRatesDriverStrategy;
import com.codingshuttle.project.uber.uberApplication.stratagies.Impl.DriverMatchingNearestDriverStrategy;
import com.codingshuttle.project.uber.uberApplication.stratagies.Impl.RideFareDefaultFareCalculationStrategy;
import com.codingshuttle.project.uber.uberApplication.stratagies.Impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalTime;

@Configuration
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatesDriverStrategy highestRatesDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RideFareDefaultFareCalculationStrategy defaultFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double rating){
        if(rating>4.8){
            return highestRatesDriverStrategy;
        }
        else{
            return nearestDriverStrategy;
        }
    }

    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime = LocalTime.of(19,0);
        LocalTime surgeEndTime = LocalTime.of(22,0);
        LocalTime currentTime = LocalTime.now();

        if(currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime)){
            return surgePricingFareCalculationStrategy;
        }
        else {
            return defaultFareCalculationStrategy;
        }
    }

}
