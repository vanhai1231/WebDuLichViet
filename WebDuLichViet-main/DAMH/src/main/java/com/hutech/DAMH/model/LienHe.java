package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LienHe")
public class LienHe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "SDT")
    private int sdt;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "DiaChi", columnDefinition = "nvarchar(250)")
    private String diaChi;

    // Constructors, getters and setters
}

