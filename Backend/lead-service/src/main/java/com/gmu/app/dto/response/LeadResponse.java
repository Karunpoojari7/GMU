package com.gmu.app.dto.response;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LeadResponse {
    private Integer enquiryNo;
    private String source;
    private String status;
    private String admissionYear;
    private LocalDate enquiryDate;
    private String college;
    private String collegeName;
    private String programme;
    private String course;
    private String discipline;
    private String description;
    private String name;
    private String mobileNo;
    private String email;
    private String collegeStudied;
    private String state;
    private String district;
    private String taluk;
    private String opinion;
    private String interestedUniversity;
    private String interestedLocation;
    private Integer callCount;
    private String callDuration;
    private LocalDate followupDate;
    private String callerId;
    private String callerName;
    private String callersEmpId;
    private LocalDate callDate;
    private String followupCollege;
    private String followupProgram;
    private String followupCourse;
    private String followupDiscipline;
    private String remarks;
    private LocalDateTime lastUpdated;
}