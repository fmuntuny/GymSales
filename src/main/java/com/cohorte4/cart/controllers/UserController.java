package com.cohorte4.cart.controllers;

import com.cohorte4.cart.entities.UserEntity;
import com.cohorte4.cart.services.users.UserService;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private UserService userService;

    public UserController(UserService userService){this.userService = userService;}

    @GetMapping
    public ResponseEntity<List<UserEntity>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public  ResponseEntity<UserEntity> findById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity userEntity){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> update(@PathVariable Long id, @RequestBody UserEntity userEntity){
        return ResponseEntity.ok(userService.update(userEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
