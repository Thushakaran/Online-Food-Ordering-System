package com.se.Online.Food.Ordering.System.controller;

import com.se.Online.Food.Ordering.System.dto.AuthResponse;
import com.se.Online.Food.Ordering.System.dto.UserDTO;
import com.se.Online.Food.Ordering.System.security.JwtUtil;
import com.se.Online.Food.Ordering.System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService; // Inject UserService

    // Login method
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        // Authenticate the user using the AuthenticationManager
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
        );

        // Load user details from the UserService
        UserDetails userDetails = userService.loadUserByUsername(userDTO.getUsername());

        // Generate the JWT token
        String token = jwtUtil.generateToken(userDetails.getUsername());

        // Return token in a structured response
        return ResponseEntity.ok(new AuthResponse(token, "Login successful"));
    }
}
