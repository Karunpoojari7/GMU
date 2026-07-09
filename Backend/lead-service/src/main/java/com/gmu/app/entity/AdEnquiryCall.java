//package com.gmu.leadservice.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "ad_enquiry_call")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class AdEnquiryCall {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "enquiry_no")
//    private Integer enquiryNo;
//
//    @Column(name = "source", nullable = false, length = 200)
//    private String source = "Agency";
//
//    @Column(name = "status", nullable = false, length = 20)
//    private String status = "ENQUIRY";
//
//    @Column(name = "admission_year", nullable = false, length = 20)
//    private String admissionYear;
//
//    @Column(name = "enquiry_date", nullable = false)
//    private LocalDate enquiryDate;
//
//    @Column(name = "college", length = 50)
//    private String college;
//
//    @Column(name = "college_name", length = 200)
//    private String collegeName;
//
//    @Column(name = "programme", length = 20)
//    private String programme;
//
//    @Column(name = "course", length = 20)
//    private String course;
//
//    @Column(name = "discipline", columnDefinition = "TEXT")
//    private String discipline;
//
//    @Column(name = "description", length = 200)
//    private String description;
//
//    @Column(name = "name", nullable = false, length = 50)
//    private String name;
//
//    @Column(name = "mobile_no", nullable = false, unique = true, length = 10)
//    private String mobileNo;
//
//    @Column(name = "email", length = 50)
//    private String email;
//
//    @Column(name = "college_studied", length = 200)
//    private String collegeStudied;
//
//    @Column(name = "state", nullable = false, length = 50)
//    private String state;
//
//    @Column(name = "district", nullable = false, length = 50)
//    private String district;
//
//    @Column(name = "taluk", nullable = false, length = 50)
//    private String taluk;
//
//    @Column(name = "opinion", length = 100)
//    private String opinion;
//
//    @Column(name = "interested_university", length = 50)
//    private String interestedUniversity;
//
//    @Column(name = "interested_location", length = 50)
//    private String interestedLocation;
//
//    @Column(name = "call_count")
//    private Integer callCount = 0;
//
//    @Column(name = "call_duration", length = 50)
//    private String callDuration;
//
//    @Column(name = "followup_date")
//    private LocalDate followupDate;
//
//    @Column(name = "caller_id", length = 50)
//    private String callerId;
//
//    @Column(name = "caller_name", length = 50)
//    private String callerName;
//
//    @Column(name = "callers_emp_id", length = 20)
//    private String callersEmpId;
//
//    @Column(name = "call_date")
//    private LocalDate callDate;
//
//    @Column(name = "followup_college", length = 200)
//    private String followupCollege;
//
//    @Column(name = "followup_program", length = 20)
//    private String followupProgram;
//
//    @Column(name = "followup_course", length = 20)
//    private String followupCourse;
//
//    @Column(name = "followup_discipline", length = 50)
//    private String followupDiscipline;
//
//    @Column(name = "REMARKS", length = 200)
//    private String remarks; // Fixed to camelCase
//
//    @Column(name = "LAST_UPDATED", nullable = false)
//    private LocalDateTime lastUpdated; // Fixed to camelCase
//}
//package com;

