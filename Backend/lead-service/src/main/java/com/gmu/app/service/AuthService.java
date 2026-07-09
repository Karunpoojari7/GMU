package com.gmu.app.service;

import com.gmu.app.dto.request.LoginRequest;
import com.gmu.app.dto.request.RegisterRequest;
import com.gmu.app.dto.response.UserResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    UserResponse createCounsellor(RegisterRequest request);
    UserResponse createLeadManager(RegisterRequest request);
    UserResponse createAdmin(RegisterRequest request);
    UserResponse getUser();
    void login(LoginRequest request, HttpServletResponse response);
    void refreshToken(HttpServletRequest request, HttpServletResponse response);
    void logout(HttpServletRequest request, HttpServletResponse response);
}