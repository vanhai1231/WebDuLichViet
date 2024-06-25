package com.hutech.DAMH.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ChiTietKhuyenMai")
public class ChiTietKhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long MaChiTietGiam;


    @ManyToOne
    @JoinColumn(name = "id_maTour")
    private Tour maTour;

    @ManyToOne
    @JoinColumn(name = "id_maKH")
    private KhuyenMai maKM;


    private String GhiChu;

}
