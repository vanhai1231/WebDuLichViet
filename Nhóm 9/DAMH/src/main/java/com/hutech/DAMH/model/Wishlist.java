package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistID;

    @OneToOne
    @JoinColumn(name = "id")
    private TaiKhoan taiKhoan;
}
