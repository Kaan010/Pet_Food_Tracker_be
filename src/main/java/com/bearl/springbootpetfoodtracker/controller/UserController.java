package com.bearl.springbootpetfoodtracker.controller;

import com.bearl.springbootpetfoodtracker.model.User;
import com.bearl.springbootpetfoodtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(
                userService.saveUser(user),
                HttpStatus.OK
        );
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getByUserName(@PathVariable String username){
        return new ResponseEntity<>(
                userService.findByUsername(username),
                HttpStatus.OK
        );
    }



}
