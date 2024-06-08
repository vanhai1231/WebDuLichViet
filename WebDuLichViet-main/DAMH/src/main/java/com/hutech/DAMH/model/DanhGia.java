package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DanhGia")
public class DanhGia {

    @Id
    @Column(name = "MaDG", nullable = false, length = 10)
    private String maDG;

    @Column(name = "DanhGia", nullable = false, length = 200)
    private String danhGia;

    @Column(name = "HinhAnh", nullable = false, length = 250)
    private String hinhAnh;

    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private TaiKhoan taiKhoan;

    // Getters and setters
}
