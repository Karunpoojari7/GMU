package com.gmu.app.service;

import com.gmu.app.dto.response.UserResponse;

public interface UserService {
    UserResponse getUserByEmpId(String empId);
}