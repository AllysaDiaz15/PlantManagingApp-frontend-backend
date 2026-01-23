package com.example.plant_app.controller;

import com.example.plant_app.dto.RegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.plant_app.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.plant_app.repository.UserRepository;
import com.example.plant_app.dto.LoginRequest;

@CrossOrigin
@RestController
@RequestMapping(path="/user")
public class UserController{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/register")
    public String addNewUser(@RequestBody @Valid RegistrationRequest request) {

        if (userRepository.findByUsername(request.getUsername()) != null) {
            return "Username already taken";
        }
        if (userRepository.findByEmail(request.getEmail()) != null) {
            return "Email already registered";
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return "Registered";
    }
    @PostMapping(path = "/login")
    public User login(@RequestBody @Valid LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            return null;
        }
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}