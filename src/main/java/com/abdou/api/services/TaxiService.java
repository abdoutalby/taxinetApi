package com.abdou.api.services;

 import com.abdou.api.models.Taxi; 

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface TaxiService {
    List<Taxi> getAllDevices();
     Taxi addDevice(Taxi  taxi);
    Taxi getById(Long id);
    public ResponseEntity<?> getByOwner(Long id);
}
