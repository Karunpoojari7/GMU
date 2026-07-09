//package com.gmu.leadservice.entity;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "gmu_audit")
//@Data
//public class GmuAudit {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "datetime", nullable = false)
//    private LocalDateTime datetime;
//
//    @Column(name = "ip", length = 45)
//    private String ip;
//
//    @Column(name = "user", length = 100)
//    private String user;
//
//    @Column(name = "table_name") // 'table' is a reserved word, use 'table_name'
//    private String tableName;
//
//    @Column(name = "action", length = 20)
//    private String action;
//
//    @Column(name = "description", columnDefinition = "TEXT")
//    private String description;
//}


