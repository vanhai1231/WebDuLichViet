package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "LoaiTour")
public class LoaiTour {

    @Id
    @Column(name = "MaLoaiTour", length = 10)
    private String maLoaiTour;

    @Column(name = "LoaiTour", length = 50)
    private String loaiTour;

    @OneToMany(mappedBy = "loaiTour", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Tour> tours;

    // Constructors, getters, and setters
}
