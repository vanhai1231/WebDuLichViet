package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TienNghi")
public class TienNghi {

    @Id
    @Column(name = "MaTienNghi")
    private String maTienNghi;

    @Column(name = "tenTN")
    private String tenTienNghi;

    // Getters and setters
}

