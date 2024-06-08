package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HuongDanVien")
public class HuongDanVien {

    @Id
    @Column(name = "MaHDV")
    private String maHDV;

    @Column(name = "TenHDV")
    private String tenHDV;

    // Getters and setters
}

