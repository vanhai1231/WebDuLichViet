package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaNV;

    @Column(name = "TenNhanVien")
    private String tenNhanVien;

    @Column(name = "SDT")
    private String sDT;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "Email")
    private String email;

    @OneToMany(mappedBy = "ID")
    private List<TaiKhoan> taiKhoans;

    @Column(name = "ID", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private TaiKhoan taiKhoan;
    // Getters and Setters
}

