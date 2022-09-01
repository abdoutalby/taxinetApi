package com.abdou.api.repo;

import com.abdou.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
