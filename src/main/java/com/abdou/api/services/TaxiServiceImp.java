package com.abdou.api.services;

 import com.abdou.api.models.Taxi;
import com.abdou.api.models.User;
import com.abdou.api.repo.TaxiRepo;
import com.abdou.api.repo.UserRepo;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
 
import java.util.List;
import java.util.Optional; 

@Service
 public class TaxiServiceImp implements TaxiService {
    @Autowired
    TaxiRepo taxiRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public List<Taxi> getAllDevices() {
        return taxiRepo.findAll();
    }

    

    @Override
    public Taxi addDevice(Taxi  dto) {
         return taxiRepo.save(dto);
    }

    @Override
    public Taxi getById(Long id) {
            return this.taxiRepo.findById(id).get();
    }


    @Override
    public ResponseEntity<?> getByOwner(Long id) {
      
         Optional<User> owner = userRepo.findById(id);
        if(owner.isPresent()){
            return ResponseEntity.ok().body(taxiRepo.findByOwner(owner.get()));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no owner with this is");
        }
    }
}
