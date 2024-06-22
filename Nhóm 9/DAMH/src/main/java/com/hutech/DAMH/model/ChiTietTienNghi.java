package com.hutech.DAMH.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ChiTietTienNghi")
public class ChiTietTienNghi {

    @Id
    @Column(name = "MaPhong", nullable = false, length = 10)
    private String maPhong;

    @Id
    @Column(name = "MaTienNghi", nullable = false, length = 10)
    private String maTienNghi;

    @ManyToOne
    @JoinColumn(name = "MaPhong", insertable = false, updatable = false)
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "MaTienNghi", insertable = false, updatable = false)
    private TienNghi tienNghi;

    // Constructors, getters, setters
    public ChiTietTienNghi() {}

    public String getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(int id) {
        this.maTienNghi = maTienNghi;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

}
