package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LoaiPhong")
public class LoaiPhong {

    @Id
    @Column(name = "MaLoai")
    private String maLoai;

    @Column(name = "TenLoai")
    private String tenLoai;

    // Getters and setters
}
