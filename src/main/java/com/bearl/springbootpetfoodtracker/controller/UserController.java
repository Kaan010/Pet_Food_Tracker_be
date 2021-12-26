package com.bearl.springbootpetfoodtracker.controller;

import com.bearl.springbootpetfoodtracker.model.User;
import com.bearl.springbootpetfoodtracker.model.dto.UserInput;
import com.bearl.springbootpetfoodtracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://127.0.0.1:8080/")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    @CrossOrigin(origins = "http://localhost:8080")
   // @PostMapping("/sign-up")
    @RequestMapping(
            value = "/sign-up",
            produces = "application/json",
            method = {RequestMethod.POST})
    public ResponseEntity<?> signUp(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()).isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

//   @PostMapping("/sign-in")
    @RequestMapping(
            value = "/sign-in",
            produces = "application/json",
            method = {RequestMethod.POST})
    public ResponseEntity<?> checkUserPresentByUserNameAndPassword(@RequestBody UserInput userInput) {
        return new ResponseEntity<>(
                userService.findByUsernameAndPassword(userInput.getUsername(), userInput.getPassword()),
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
