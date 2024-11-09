package com.project.uber.uberApplication.controllers;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RideStartDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId, @RequestBody RideStartDto rideStartDto){
        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDto.getOtp()));
    }

    @PostMapping("/cancelRide/{rideRequestId}")
    public  ResponseEntity<RideDto> cancelRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.cancelRide(rideRequestId));
    }

    @PostMapping("/endRide/{rideRequestId}")
    public  ResponseEntity<RideDto> endRide(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(driverService.endRide(rideRequestId));
    }

    @GetMapping("/getMyProfile")
    public  ResponseEntity<DriverDto> getMyProfile(){
        return ResponseEntity.ok(driverService.getMyProfile());
    }

    @PostMapping("/rateRider/{rideId}")
    public ResponseEntity<RiderDto> rateRider(@PathVariable Long rideId){
        return  ResponseEntity.ok(driverService.rateRider(rideId));
    }

    @GetMapping("/getAllMyRides")
    public  ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                        @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize);
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));
    }
}
