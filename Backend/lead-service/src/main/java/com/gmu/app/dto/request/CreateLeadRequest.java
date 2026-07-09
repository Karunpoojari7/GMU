package com.gmu.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateLeadRequest {
	
	

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @Size(max = 200, message = "Source length cannot exceed 200 characters")
    private String source;

    @NotBlank(message = "Applicant name is required")
    @Size(max = 50, message = "Name cannot exceed 50 characters")
    private String name;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNo;

    @Email(message = "Please provide a valid email address")
    @Size(max = 50, message = "Email cannot exceed 50 characters")
    private String email;

    @Size(max = 200, message = "College studied cannot exceed 200 characters")
    private String collegeStudied;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @NotBlank(message = "District is required")
    @Size(max = 50, message = "District cannot exceed 50 characters")
    private String district;

    @NotBlank(message = "Taluk is required")
    @Size(max = 50, message = "Taluk cannot exceed 50 characters")
    private String taluk;

    @Size(max = 20, message = "Programme selection cannot exceed 20 characters")
    private String programme;

    @Size(max = 20, message = "Course selection cannot exceed 20 characters")
    private String course;
}