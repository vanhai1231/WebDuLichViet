package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "HinhAnh")
public class HinhAnh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "img", nullable = false, length = 250)
    private String img;

    // Mối quan hệ: Một hình ảnh có thể có nhiều hình ảnh chi tiết
    @OneToMany(mappedBy = "hinhAnh")
    private List<Images> imagesList;
    // Getters and setters
}

