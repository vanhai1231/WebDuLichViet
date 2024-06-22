package com.hutech.DAMH.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
}
