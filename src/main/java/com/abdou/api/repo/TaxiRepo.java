package com.abdou.api.repo;

 import com.abdou.api.models.Taxi;
import com.abdou.api.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiRepo extends JpaRepository<Taxi , Long> {
    List<Taxi> findByOwner(User owner);
}
