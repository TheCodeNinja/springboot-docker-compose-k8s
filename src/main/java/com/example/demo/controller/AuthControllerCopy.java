package com.example.demo.controller;

import com.example.demo.annotation.ApiResponse;
import com.example.demo.dto.*;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

//@Slf4j
//@RestController
//@RequestMapping("/auth")
//@RequiredArgsConstructor
//@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthControllerCopy {

//    private final AuthenticationManager authenticationManager;
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenProvider tokenProvider;
//
//
//    @PostMapping("/signin")
//    @Operation(summary = "Sign in user", description = "Authenticate user and return JWT token")
//    @ApiResponse(responseCode = 200, description = "Successfully authenticated", response = JwtResponse.class)
//    @ApiResponse(responseCode = 401, description = "Authentication failed", response = ErrorResponse.class)
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        log.info("Signin request received for user: {}", loginRequest.getUsername());
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            loginRequest.getPassword()
//                    )
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = tokenProvider.generateToken(authentication);
//            log.info("Successfully authenticated user: {}", loginRequest.getUsername());
//
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//            User user = userRepository.findByUsername(userDetails.getUsername())
//                    .orElseThrow(() -> new RuntimeException("User not found"));
//
//            return ResponseEntity.ok(new JwtResponse(
//                    jwt,
//                    "Bearer",
//                    user.getId(),
//                    user.getUsername(),
//                    user.getEmail(),
//                    userDetails.getAuthorities()
//            ));
//        } catch (Exception e) {
//            log.error("Authentication failed for user: {}, error: {}", loginRequest.getUsername(), e.getMessage());
//            throw e;
//        }
//    }
//
//    @PostMapping("/signup")
//    @Operation(summary = "Register user", description = "Register a new user")
//    @ApiResponse(responseCode = 200, description = "User successfully registered", response = MessageResponse.class)
//    @ApiResponse(responseCode = 400, description = "Invalid input", response = ErrorResponse.class)
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return ResponseEntity.badRequest()
//                    .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        User user = new User();
//        user.setUsername(signUpRequest.getUsername());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(Role.ROLE_USER);
//        user.setRoles(roles);
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//    }
//
//    @GetMapping("/log")
//    public ResponseEntity<String> generateLog() {
//        log.info("This is a test log message");
//        log.debug("This is a debug message");
//        log.error("This is an error message");
//        return ResponseEntity.ok("Logs generated");
//    }
}