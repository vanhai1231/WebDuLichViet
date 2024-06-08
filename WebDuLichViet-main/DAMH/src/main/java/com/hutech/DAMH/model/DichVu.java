package com.hutech.DAMH.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "DichVu")
public class DichVu {

    @Id
    @Column(name = "MaDV", nullable = false, length = 10)
    private String maDV;

    @Column(name = "TenDV", nullable = false, length = 100)
    private String tenDV;

    @Column(name = "GiaDV", nullable = false)
    private BigDecimal giaDV;

    // Getters and setters
}

