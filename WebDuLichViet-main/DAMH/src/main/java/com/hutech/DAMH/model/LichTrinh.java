package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LichTrinh")
public class LichTrinh {

    @Id
    @Column(name = "NgayKH", nullable = false)
    private Date ngayKH;

    // Getters and setters
}

