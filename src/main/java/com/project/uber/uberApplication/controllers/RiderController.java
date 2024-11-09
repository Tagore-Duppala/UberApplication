package com.project.uber.uberApplication.controllers;

import com.project.uber.uberApplication.dto.DriverDto;
import com.project.uber.uberApplication.dto.RideDto;
import com.project.uber.uberApplication.dto.RideRequestDto;
import com.project.uber.uberApplication.dto.RiderDto;
import com.project.uber.uberApplication.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/rider")
@RequiredArgsConstructor
public class RiderController {

    private final RiderService riderService;

    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));

    }

    @PostMapping("/cancelRide/{rideRequestId}")
    public  ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }

    @PostMapping("/cancelRideRequest/{rideRequestId}")
    public  ResponseEntity<RideRequestDto> cancelRideRequest(@PathVariable Long rideRequestId){
        return ResponseEntity.ok(riderService.cancelRideRequest(rideRequestId));
    }

    @GetMapping("/getMyProfile")
    public  ResponseEntity<RiderDto> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyProfile());
    }

    @PostMapping("/rateDriver/{rideId}")
    public ResponseEntity<DriverDto> rateRider(Long rideId){
        return  ResponseEntity.ok(riderService.rateDriver(rideId));
    }

    @GetMapping("/getAllMyRides")
    public  ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                        @RequestParam(defaultValue = "10") Integer pageSize){
        PageRequest pageRequest = PageRequest.of(pageOffset,pageSize);
        return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
    }

}
