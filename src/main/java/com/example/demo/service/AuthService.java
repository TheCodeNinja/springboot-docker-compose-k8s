package com.example.demo.service;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.MessageResponse;
import com.example.demo.dto.SignupRequest;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    // Inject RedisTemplate for Redis operations
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String REDIS_USER_PREFIX = "user:";

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        log.info("Attempting to authenticate user: {}", loginRequest.getUsername());
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User user = userRepository.findByUsername(userDetails.getUsername())
                    .orElseThrow(() -> {
                        log.error("User not found after successful authentication: {}", loginRequest.getUsername());
                        return new RuntimeException("User not found");
                    });

            // Store user session in Redis (best practice for scalable session management)
            String redisKey = REDIS_USER_PREFIX + user.getUsername();
            redisTemplate.opsForValue().set(redisKey, jwt, 1, TimeUnit.HOURS);

            log.info("User authenticated successfully: {}", user.getUsername());

            return new JwtResponse(
                    jwt,
                    "Bearer",
                    user.getId(),
                    user.getUsername(),
                    user.getEmail(),
                    userDetails.getAuthorities()
            );
        } catch (AuthenticationException e) {
            log.error("Authentication failed for user: {}", loginRequest.getUsername(), e);
            throw e; // Will be handled by GlobalExceptionHandler
        }
    }

    public MessageResponse registerUser(SignupRequest signUpRequest) {
        log.info("Registering new user: {}", signUpRequest.getUsername());

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            log.warn("Registration failed: Username '{}' is already taken", signUpRequest.getUsername());
            throw new IllegalArgumentException("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            log.warn("Registration failed: Email '{}' is already in use", signUpRequest.getEmail());
            throw new IllegalArgumentException("Error: Email is already in use!");
        }

        try {
            User user = new User();
            user.setUsername(signUpRequest.getUsername());
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_USER);
            user.setRoles(roles);

            userRepository.save(user);

            log.info("User registered successfully: {}", user.getUsername());
            return new MessageResponse("User registered successfully!");
        } catch (Exception e) {
            log.error("An error occurred while registering user: {}", signUpRequest.getUsername(), e);
            throw new RuntimeException("An unexpected error occurred during user registration.");
        }
    }

    public void logTestMessages() {
        log.info("This is a test log message");
        log.debug("This is a debug message");
        log.error("This is an error message");
    }
}