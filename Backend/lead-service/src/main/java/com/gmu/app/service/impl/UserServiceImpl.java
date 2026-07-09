package com.gmu.app.service.impl;

import org.springframework.stereotype.Service;

import com.gmu.app.dto.response.UserResponse;
import com.gmu.app.entity.User;
import com.gmu.app.exception.NotFoundException;
import com.gmu.app.mapper.UserMapper;
import com.gmu.app.repository.UserRepository;
import com.gmu.app.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserResponse getUserByEmpId(String empId) {
        User user = userRepository.findByEmpId(empId)
                .orElseThrow(() -> new NotFoundException("User not found with Employee ID: " + empId));

        return mapper.toResponse(user);
    }
}