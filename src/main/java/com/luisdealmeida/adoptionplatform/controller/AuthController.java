package com.luisdealmeida.adoptionplatform.controller;

import com.luisdealmeida.adoptionplatform.dto.LoginRequest;
import com.luisdealmeida.adoptionplatform.dto.LoginResponse;
import com.luisdealmeida.adoptionplatform.exception.ApiErrorResponse;
import com.luisdealmeida.adoptionplatform.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            ApiErrorResponse errorBody = new ApiErrorResponse(
                    Instant.now(),
                    HttpStatus.UNAUTHORIZED.value(),
                    "Invalid credentials",
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody);
        }

        String token = jwtUtil.generateToken(request.getEmail(), "STAFF");
        LoginResponse response = new LoginResponse(token, request.getEmail(), "STAFF");
        return ResponseEntity.ok(response);
    }
}
