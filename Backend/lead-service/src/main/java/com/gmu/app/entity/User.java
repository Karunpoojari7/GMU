package com.gmu.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SL_NO")
    private Integer slNo;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 255)
    private String password;

    @Column(name = "EMP_ID", unique = true, length = 50)
    private String empId;

    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "USER_GROUP", length = 20)
    private String userGroup; // Matches your legacy authentication roles (e.g., 'ADMIN')

    @Column(name = "COLLEGE", length = 100)
    private String college;

    @Column(name = "PROGRAMME", length = 50)
    private String programme;

    @Column(name = "COURSE", length = 50)
    private String course;

    @Column(name = "DISCIPLINE", length = 50)
    private String discipline;

    @Column(name = "PHOTO", length = 255)
    private String photo;

    @Column(name = "virtual_number_flag")
    private Integer virtualNumberFlag;

    @Column(name = "status", length = 20)
    private String status;
}