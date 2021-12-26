package com.bearl.springbootpetfoodtracker.controller;

import com.bearl.springbootpetfoodtracker.model.User;
import com.bearl.springbootpetfoodtracker.model.UserDto;
import com.bearl.springbootpetfoodtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/sign-up/")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/sign-in/")
    public ResponseEntity<?> checkUserPresentByUserNameAndPassword(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(
                userService.findByUsernameAndPassword(userDto.getUsername(),userDto.getPassword()),
                HttpStatus.OK
        );
    }

//    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("{username}")
    public ResponseEntity<?> getByUserName(@PathVariable String username) {
        return new ResponseEntity<>(
                userService.findByUsername(username),
                HttpStatus.OK
        );
    }


}
