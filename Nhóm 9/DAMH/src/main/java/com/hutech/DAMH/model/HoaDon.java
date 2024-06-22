package com.hutech.DAMH.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @Column(name = "MaHD", nullable = false, length = 10)
    private String maHD;

    @Column(name = "TongTien", nullable = false)
    private BigDecimal tongTien;

    @Column(name = "Ngaylap", nullable = false)
    private Date ngayLap;

    @Column(name = "MaTour", nullable = false, length = 10)
    private String maTour;

    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "MaTour", insertable = false, updatable = false)
    private Tour tour;

    // Getters and setters
}

