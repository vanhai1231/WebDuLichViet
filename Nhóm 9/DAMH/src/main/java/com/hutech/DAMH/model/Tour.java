package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Tour")
public class Tour {

    @Id
    @Column(name = "MaTour")
    private String maTour;

    @Column(name = "TenTour")
    private String tenTour;

    @Column(name = "NgayKH")
    private Date ngayKH;

    @Column(name = "NoiKhoiHanh")
    private String noiKhoiHanh;

    @Column(name = "GiaTour")
    private int giaTour;

    @ManyToOne
    @JoinColumn(name = "MaPhuongTien")
    private PhuongTien phuongTien;

    @ManyToOne
    @JoinColumn(name = "MaTinh")
    private Tinh tinh;

    @Column(name = "Soluong")
    private int soluong;

    @ManyToOne
    @JoinColumn(name = "MaPhong")
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "MaLoaiTour")
    private LoaiTour loaiTour;

    @ManyToOne
    @JoinColumn(name = "MaKM")
    private KhuyenMai khuyenMai;

    @Transient
    private List<String> imageUrls;

    @Transient
    private String mainImageUrl;
    @Transient
    private String secondaryImageUrl;
    public String getSecondaryImageUrl() {
        return secondaryImageUrl;
    }
    public void setSecondaryImageUrl(String secondaryImageUrl) {
        this.secondaryImageUrl = secondaryImageUrl;
    }

    @Transient
    private List<String> otherImageUrls;

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getMainImageUrl() {
        return this.mainImageUrl;
    }

    public void setOtherImageUrls(List<String> otherImageUrls) {
        this.otherImageUrls = otherImageUrls;
    }

    public List<String> getOtherImageUrls() {
        return this.otherImageUrls;
    }
    // Getters and setters
}