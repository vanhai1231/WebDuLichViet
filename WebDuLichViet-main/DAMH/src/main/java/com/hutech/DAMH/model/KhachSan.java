package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "KhachSan")
public class KhachSan {

    @Id
    @Column(name = "MaKS")
    private String maKS;

    @Column(name = "TenKS")
    private String tenKS;

    @Column(name = "DiaChiChi")
    private String diaChiChi;

    @ManyToOne
    @JoinColumn(name = "MaDG")
    private DanhGia danhGia;
    // Getters and setters
}

