package com.gmu.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.gmu.app.dto.request.LoginRequest;
import com.gmu.app.dto.request.RegisterRequest;
import com.gmu.app.dto.response.UserResponse;
import com.gmu.app.response.ApiResponse;
import com.gmu.app.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leads/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/counsellors")
    public ResponseEntity<ApiResponse<UserResponse>> createCounsellor(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.createCounsellor(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().success(true)
                .message("Counsellor created successfully").data(response).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/lead-managers")
    public ResponseEntity<ApiResponse<UserResponse>> createLeadManager(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.createLeadManager(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().success(true)
                .message("Lead manager created successfully").data(response).build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/users/admins")
    public ResponseEntity<ApiResponse<UserResponse>> createAdmin(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.createAdmin(request);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().success(true)
                .message("Admin created successfully").data(response).build());
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getUser() {
        UserResponse response = authService.getUser();
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder().success(true)
                .message("User fetched successfully").data(response).build());
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        authService.login(request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh")
    public ResponseEntity<Void> refresh(HttpServletRequest request, HttpServletResponse response) {
        authService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok().build();
    }
}