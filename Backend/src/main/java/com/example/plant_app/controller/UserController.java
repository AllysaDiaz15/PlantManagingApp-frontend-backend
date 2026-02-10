package com.example.plant_app.controller;

import com.example.plant_app.dto.LoginResponse;
import com.example.plant_app.dto.RegistrationRequest;
import com.example.plant_app.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUserId(), user.getUsername());
        return ResponseEntity.ok(new LoginResponse(user.getUserId(), user.getUsername(), token));
    }

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
}
