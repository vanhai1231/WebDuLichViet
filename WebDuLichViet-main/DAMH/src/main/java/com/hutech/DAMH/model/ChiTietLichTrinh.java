package com.hutech.DAMH.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ChiTietLichTrinh")
public class ChiTietLichTrinh implements Serializable {

    @Id
    @Column(name = "MaTour", nullable = false, length = 10)
    private String maTour;

    @Id
    @Column(name = "NgayKH", nullable = false)
    private Date ngayKH;

    @Id
    @Column(name = "MaHDV", nullable = false, length = 10)
    private String maHDV;

    @ManyToOne
    @JoinColumn(name = "MaHDV", insertable = false, updatable = false)
    private HuongDanVien huongDanVien;

    @ManyToOne
    @JoinColumn(name = "NgayKH", insertable = false, updatable = false)
    private LichTrinh lichTrinh;

    @ManyToOne
    @JoinColumn(name = "MaTour", insertable = false, updatable = false)
    private Tour tour;

    // Getters and setters
}

