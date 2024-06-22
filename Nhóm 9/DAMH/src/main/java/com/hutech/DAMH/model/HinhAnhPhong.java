package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HinhAnhPhong")
public class HinhAnhPhong {

    @Id
    @Column(name = "MaPhong", nullable = false, length = 10)
    private String maPhong;

    @Id
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private HinhAnh hinhAnh;

    @ManyToOne
    @JoinColumn(name = "MaPhong", insertable = false, updatable = false)
    private Phong phong;

    // Getters and setters
}

