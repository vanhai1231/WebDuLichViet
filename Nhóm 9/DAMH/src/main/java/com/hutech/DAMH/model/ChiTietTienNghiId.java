package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietTienNghiId implements Serializable {
    @Column(name = "MaPhong")
    private String maPhong;

    @Column(name = "MaTienNghi")
    private String maTienNghi;

    // Constructors, getters, setters, hashCode, equals
    public ChiTietTienNghiId() {}

    public ChiTietTienNghiId(String maPhong, String maTienNghi) {
        this.maPhong = maPhong;
        this.maTienNghi = maTienNghi;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getMaTienNghi() {
        return maTienNghi;
    }

    public void setMaTienNghi(String maTienNghi) {
        this.maTienNghi = maTienNghi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietTienNghiId that = (ChiTietTienNghiId) o;
        return Objects.equals(maPhong, that.maPhong) && Objects.equals(maTienNghi, that.maTienNghi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maPhong, maTienNghi);
    }
}
