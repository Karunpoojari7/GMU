package com.gmu.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private String empId;
    private String username;
    private String name;
    private String userGroup;

}