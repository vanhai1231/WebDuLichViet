package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ThanhPho")
public class ThanhPho {
    @Id
    @Column(name = "MaTP", nullable = false, length = 10)
    private String maTP;

    @Column(name = "TenTP", length = 50)
    private String tenTP;

    @ManyToOne
    @JoinColumn(name = "MaTinh", insertable = false, updatable = false)
    private Tinh tinh;
}
