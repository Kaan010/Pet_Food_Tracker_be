package com.bearl.springbootpetfoodtracker.service;

import com.bearl.springbootpetfoodtracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bearl.springbootpetfoodtracker.repository.IUserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public User saveUser(User user){

        user.setId(0L); //dummy id(will be incremented later levels)
        user.setName(user.getName());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setCurrentFoodGram(user.getCurrentFoodGram());
        user.setCreateTime(LocalDateTime.now());

        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByUsernameAndPassword(String username,String password){
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
