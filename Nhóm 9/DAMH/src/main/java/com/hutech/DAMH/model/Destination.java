package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Entity
@Table(name = "DiemDen")
public class Destination {
    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "TenDiemDen", nullable = false, length = 200)
    private String tenDiemDen;

    @Column(name = "HinhAnh", nullable = false, length = 200)
    private String hinhAnh;

    @Column(name = "MoTa", nullable = false, length = 1000)
    private String moTa;
    @Transient
    private MultipartFile hinhAnhFile;
    @ManyToOne
    @JoinColumn(name = "MaTinh", updatable = false)
    private Tinh maTinh;

}
