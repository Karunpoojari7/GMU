package com.gmu.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gmu.app.dto.response.UserResponse;
import com.gmu.app.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/employee/{empId}")
    public ResponseEntity<UserResponse> getUserByEmpId(@PathVariable String empId) {
        return ResponseEntity.ok(userService.getUserByEmpId(empId));
    }
}