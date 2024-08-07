package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ChiTietKhuyenMaiId.class)
@Table(name = "ChiTietKhuyenMai")
public class ChiTietKhuyenMai {

    @Id
    @Column(name = "MaTour", nullable = false, length = 10)
    private String maTour;

    @Id
    @Column(name = "MaKM", nullable = false)
    private String maKM;

    @ManyToOne
    @JoinColumn(name = "MaTour", insertable = false, updatable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "MaKM", insertable = false, updatable = false)
    private KhuyenMai khuyenMai;
}
