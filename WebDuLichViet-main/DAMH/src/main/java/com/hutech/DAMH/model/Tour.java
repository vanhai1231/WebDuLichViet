package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @Column(name = "MaTour")
    private String maTour;

    @Column(name = "NgayKH")
    private Date ngayKH;

    @Column(name = "NoiKhoiHanh")
    private String noiKhoiHanh;

    @ManyToOne
    @JoinColumn(name = "MaPhuongTien")
    private PhuongTien phuongTien;

    @ManyToOne
    @JoinColumn(name = "MaTinh")
    private Tinh tinh;

    @Column(name = "Soluong")
    private int soluong;

    @ManyToOne
    @JoinColumn(name = "MaPhong")
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "MaLoaiTour")
    private LoaiTour loaiTour;

    @ManyToOne
    @JoinColumn(name = "MaKM")
    private KhuyenMai khuyenMai;

    // Getters and setters
}