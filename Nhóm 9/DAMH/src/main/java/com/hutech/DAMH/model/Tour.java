package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") // Specify the date-time format expected
    private Date ngayKH;

    @Column(name = "NoiKhoiHanh")
    private String noiKhoiHanh;

    @Column(name = "GiaTour")
    private int giaTour;

    @ManyToOne
    @JoinColumn(name = "MaPhuongTien")
    @JsonManagedReference
    private PhuongTien phuongTien;

    @ManyToOne
    @JoinColumn(name = "MaTinh")
    @JsonManagedReference
    private Tinh tinh;

    @Column(name = "Soluong")
    private int soluong;

    @ManyToOne
    @JoinColumn(name = "MaPhong")
    @JsonManagedReference
    private Phong phong;

    @ManyToOne
    @JoinColumn(name = "MaLoaiTour")
    @JsonBackReference
    private LoaiTour loaiTour;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<ChiTietWishlist> chiTietWishlists;

    @Transient
    private List<String> imageUrls;

    @Transient
    private String mainImageUrl;

    @Transient
    private String secondaryImageUrl;

    @Transient
    private List<String> otherImageUrls;
    @Transient
    private int giaKhuyenMai;
    @Transient
    private boolean promotionActive;
    @Transient
    private int phanTramGiam;
    @Transient
    private Date ngayBatDauKM;
    @Transient
    private Date ngayKetThucKM;

    // Getters and setters
    public boolean isPromotionActive() {
        return promotionActive;
    }

    public void setPromotionActive(boolean promotionActive) {
        this.promotionActive = promotionActive;
    }

    // Getter và setter cho thuộc tính phanTramGiam
    public int getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(int phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    // Getter và setter cho ngày bắt đầu và kết thúc khuyến mãi
    public Date getNgayBatDauKM() {
        return ngayBatDauKM;
    }

    public void setNgayBatDauKM(Date ngayBatDauKM) {
        this.ngayBatDauKM = ngayBatDauKM;
    }

    public Date getNgayKetThucKM() {
        return ngayKetThucKM;
    }

    public void setNgayKetThucKM(Date ngayKetThucKM) {
        this.ngayKetThucKM = ngayKetThucKM;
    }

    public int getGiaKhuyenMai() {
        return phanTramGiam;
    }

    public void setGiaKhuyenMai(int giaKhuyenMai) {
        this.giaKhuyenMai = giaKhuyenMai;
    }
    // Getters and setters
}
