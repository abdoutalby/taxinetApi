package com.abdou.api.repo;

import com.abdou.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Role findByName(String name);
}
