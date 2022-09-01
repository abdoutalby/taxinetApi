package com.abdou.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abdou.api.models.Ride;

public interface RideRepo extends JpaRepository<Ride , Long>{
    
}
