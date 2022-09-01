package com.abdou.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abdou.api.models.Ride;
import com.abdou.api.repo.RideRepo;


@Service
public class RideServiceImp implements RideService {
    

    @Autowired
    RideRepo rideRepo;

    @Override
    public ResponseEntity<?> getAll() { 
        return ResponseEntity.ok().body( rideRepo.findAll());
    }

    @Override
    public ResponseEntity<?> getByTaxi(Long id) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok().body(rideRepo.findById(id));
    }

    @Override
    public ResponseEntity<?> save(Ride ride) {
        // TODO Auto-generated method stub
        return ResponseEntity.ok( ).body(rideRepo.save(ride));
    }
    
}
