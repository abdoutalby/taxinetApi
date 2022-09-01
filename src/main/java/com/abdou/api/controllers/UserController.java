package com.abdou.api.controllers;

import com.abdou.api.models.Role;
import com.abdou.api.models.User;
import com.abdou.api.services.UserService;
import com.abdou.api.utils.RoleAffectForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

 @RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {
    private final UserService userService;


    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }



    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(
                ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("user/addRole")
    public ResponseEntity<?> affectRole(@RequestBody RoleAffectForm form ){
        userService.affectRole(form.getEmail(), form.getRole());
        return  ResponseEntity.ok().build ();
    }


}
