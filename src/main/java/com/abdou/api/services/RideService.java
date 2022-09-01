package com.abdou.api.services;

import org.springframework.http.ResponseEntity;

import com.abdou.api.models.Ride;

public interface RideService {
    ResponseEntity<?> getAll();
    ResponseEntity<?> getByTaxi(Long id);
    ResponseEntity<?> save(Ride ride);
}
