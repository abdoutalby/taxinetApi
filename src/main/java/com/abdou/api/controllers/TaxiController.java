package com.abdou.api.controllers;

 import com.abdou.api.models.Taxi;
 import com.abdou.api.services.TaxiServiceImp;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/taxi/")
@RequiredArgsConstructor
public class TaxiController {

    private final TaxiServiceImp taxiService;

    @GetMapping()
    public ResponseEntity<List<Taxi>> getAll() {
        return ResponseEntity.ok().body(taxiService.getAllDevices());
    }

    @PostMapping()
    public ResponseEntity<Taxi> save (@RequestBody Taxi dto) {
        return ResponseEntity.ok().body(taxiService.addDevice(dto));
    }
   
    @GetMapping(value ="byId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body( taxiService.getById(id));
    }
    
    @GetMapping(value ="byOwner/{owner}")
    public ResponseEntity<?> getByOwner(@PathVariable("owner") Long id){
        return taxiService.getByOwner(id);
    }

}
