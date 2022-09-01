package com.abdou.api.controllers;


import com.abdou.api.config.JwtProvider;
import com.abdou.api.models.Role;
import com.abdou.api.models.User;
import com.abdou.api.repo.RoleRepo;
import com.abdou.api.repo.UserRepo;
import com.abdou.api.utils.JwtResponse;
import com.abdou.api.utils.LoginForm;
import com.abdou.api.utils.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Transactional
@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    @Autowired
   private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider jwtProvider;


     @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        log.info("email : {} and password : {}" , loginRequest.getEmail() , loginRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()

                )
        );
        log.info("email : {} and password : {}" , loginRequest.getEmail() , loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);

        return ResponseEntity.ok(new JwtResponse(jwt));
    }

     @PostMapping(value = "/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterForm signUpRequest) {

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>("Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account

         Collection<Role> roles = new ArrayList<>();

                    if(roleRepository.findByName(signUpRequest.getRole())==null){
                        roleRepository.save(new Role(null , signUpRequest.getRole()));
                    }
                    roles.add(roleRepository.findByName(signUpRequest.getRole()));


        User user = new User(null,signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()) ,
                roles , null );

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }
}