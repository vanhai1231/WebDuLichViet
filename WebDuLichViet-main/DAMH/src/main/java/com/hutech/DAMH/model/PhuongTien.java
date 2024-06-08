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
@Table(name = "PhuongTien")
public class PhuongTien {
    @Id
    @Column(name = "MaPhuongTien", nullable = false, length = 10)
    private String maPhuongTien;

    @Column(name = "TenPhuongTien", length = 50)
    private String tenPhuongTien;
}
