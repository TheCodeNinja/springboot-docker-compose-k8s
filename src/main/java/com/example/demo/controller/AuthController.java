package com.example.demo.controller;

import com.example.demo.annotation.ApiResponse;
import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Authentication", description = "Authentication management APIs")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    @Operation(summary = "Sign in user", description = "Authenticate user and return JWT token")
    @ApiResponse(responseCode = 200, description = "Successfully authenticated", response = JwtResponse.class)
    @ApiResponse(responseCode = 401, description = "Authentication failed", response = ErrorResponse.class)
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        log.info("Signin request received for user: {}", loginRequest.getUsername());
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signup")
    @Operation(summary = "Register user", description = "Register a new user")
    @ApiResponse(responseCode = 200, description = "User successfully registered", response = MessageResponse.class)
    @ApiResponse(responseCode = 400, description = "Invalid input", response = ErrorResponse.class)
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        log.info("Signup request received for user: {}", signUpRequest.getEmail());
        MessageResponse messageResponse = authService.registerUser(signUpRequest);
        return ResponseEntity.ok(messageResponse);
    }

    @GetMapping("/log")
    public ResponseEntity<String> generateLog() {
        authService.logTestMessages();
        return ResponseEntity.ok("Logs generated");
    }
}