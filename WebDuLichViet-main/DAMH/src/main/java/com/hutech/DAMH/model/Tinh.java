package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Tinh")
public class Tinh {
    @Id
    @Column(name = "MaTinh", nullable = false, length = 10)
    private String maTinh;

    @Column(name = "TenTinh", length = 50)
    private String tenTinh;

}
