package com.hutech.DAMH.model;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietKhuyenMaiId implements Serializable {
    private String maTour;
    private String maKM;

    public ChiTietKhuyenMaiId() {
    }

    public ChiTietKhuyenMaiId(String maTour, String maKM) {
        this.maTour = maTour;
        this.maKM = maKM;
    }

    // getters, setters, hashCode and equals methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietKhuyenMaiId that = (ChiTietKhuyenMaiId) o;
        return Objects.equals(maTour, that.maTour) && Objects.equals(maKM, that.maKM);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTour, maKM);
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public String getMaKM() {
        return maKM;
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }
}
