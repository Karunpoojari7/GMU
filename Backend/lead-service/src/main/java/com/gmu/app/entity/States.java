package com.gmu.app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "states")
@Data
public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SL_NO")
    private Integer slNo;

    @Column(name = "STATE", length = 100)
    private String state;

    @Column(name = "DISTRICT", length = 100)
    private String district;

    @Column(name = "TALUK", length = 100)
    private String taluk;
}


