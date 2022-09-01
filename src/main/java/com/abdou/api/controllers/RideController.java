package com.abdou.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abdou.api.models.Ride;
import com.abdou.api.services.RideServiceImp;
import com.abdou.api.utils.RideDto;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/ride/")
public class RideController {
    

    @Autowired
    RideServiceImp rideService ;

    @GetMapping(value="")
    public ResponseEntity<?> getAll( ) {
        return  rideService.getAll();
    }

    @GetMapping(value = "/{taxi}")
    public ResponseEntity<?> getByTaxi(@PathVariable("taxi") Long id){
        return rideService.getByTaxi(id);
    }

    @PostMapping()
    public ResponseEntity<?> save (@RequestBody() RideDto ride ){
        Ride r = new Ride(null , 
        ride.getPrice(),
        ride.getFrom(),
         ride.getTo() , 
         ride.getDate(),
         ride.getTaxi()
         );
        return rideService.save(r);
    }
    
}
