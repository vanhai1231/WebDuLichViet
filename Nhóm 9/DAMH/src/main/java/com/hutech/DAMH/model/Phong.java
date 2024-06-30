package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Phong")
public class Phong {

    @Id
    @Column(name = "MaPhong")
    private String maPhong;

    @Column(name = "Gia")
    private int gia;

    @Column(name = "MoTa")
    private String moTa;

    @ManyToOne
    @JoinColumn(name = "MaKS")
    private KhachSan khachSan;

    @ManyToOne
    @JoinColumn(name = "MaLoai")
    private LoaiPhong loaiPhong;

    // Getters and setters
}
