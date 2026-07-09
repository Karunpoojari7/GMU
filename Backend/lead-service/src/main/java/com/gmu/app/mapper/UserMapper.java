package com.gmu.app.mapper;

import org.springframework.stereotype.Component;

import com.gmu.app.dto.response.UserResponse;
import com.gmu.app.entity.User;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .slNo(user.getSlNo())
                .empId(user.getEmpId())
                .username(user.getUsername())
                .name(user.getName())
                .userGroup(user.getUserGroup())
                .photo(user.getPhoto())
                .status(user.getStatus())
                .build();
    }
}