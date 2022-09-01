package com.abdou.api.services;

import com.abdou.api.models.Role;
import com.abdou.api.models.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void affectRole(String email , String role);
    User getUser(String eamil);
    List<User> getUsers();
}
