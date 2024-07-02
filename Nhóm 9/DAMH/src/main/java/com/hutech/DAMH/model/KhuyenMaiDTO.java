package com.hutech.DAMH.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KhuyenMaiDTO {
    private KhuyenMai khuyenMai;
    private String tenTour;
    private String maKM;
    private String tenKM;
    private String phanTramKM;
    private String ngayBatDau;
    private String ngayKetThuc;
    // Các thuộc tính và constructor khác


    // Constructor
    public KhuyenMaiDTO(KhuyenMai khuyenMai, String tenTour) {
        this.maKM = khuyenMai.getMaKM();
        this.tenKM = khuyenMai.getTenKM();
        this.phanTramKM = String.valueOf(khuyenMai.getPhanTramKM());
        this.ngayBatDau = khuyenMai.getNgayBatDau().toString();
        this.ngayKetThuc = khuyenMai.getNgayKetThuc().toString();
        this.tenTour = tenTour;
    }

}

