package com.abdou.api.services;

import com.abdou.api.models.Role;
import com.abdou.api.models.User;
import com.abdou.api.repo.RoleRepo;
import com.abdou.api.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImp implements UserService , UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user ==null){
            log.error("User not exist ");
            throw new UsernameNotFoundException("No user");
        }else {
            log.info("user found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
        {
            authorities.add(new SimpleGrantedAuthority( role.getName()));
        });

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),authorities);
    }


    @Override
    public User saveUser(User user) {
        log.info("adding user to DB");
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("adding role {} to DB", role);
        return roleRepo.save(role);
    }

    @Override
    public void affectRole(String email, String role) {
        log.info("adding role {} user {}   " , role ,  email);

        User user = userRepo.findByEmail(email);
        Role role1 = roleRepo.findByName(role);
        user.getRoles().add(role1);
    }

    @Override
    public User getUser(String email) {
        log.info("getting user {}" , email);

        return userRepo.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("getting users  " );

        return userRepo.findAll();
    }

}
