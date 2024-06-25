package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "KhuyenMai")
public class KhuyenMai {
    @Id
    @Column(name = "MaKM", nullable = false, length = 10)
    private String maKM;

    @Column(name = "TenKM", length = 50)
    private String tenPhuongTien;

    @Column(name = "PhanTramKM")
    private int phanTramKM;

    @Column(name = "NgayBatDau")
    private Date ngayBatDau;

    @Column(name = "NgayKetThuc")
    private Date ngayKetThuc;

    @Column(name = "SoLan")
    private boolean SoLan;

    @ManyToOne
    @JoinColumn(name = "idTaiKhoan")
    private TaiKhoan taiKhoan;
}
