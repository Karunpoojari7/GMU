package com.gmu.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {

    private Integer slNo;

    private String username;

    private String empId;

    private String phoneNo;

    private String name;

    private String userGroup;

    private String college;

    private String programme;

    private String course;

    private String discipline;

    private String photo;

    private Integer virtualNumberFlag;

    private String status;

}