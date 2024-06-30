package com.hutech.DAMH.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(ChiTietWishlistId.class)
@Getter
@Setter
public class ChiTietWishlist {
    @Id
    @Column(name = "MaTour", nullable = false, length = 10)
    private String maTour;

    @Id
    @Column(name = "WishlistID", nullable = false)
    private int wishlistID;

    @ManyToOne
    @JoinColumn(name = "WishlistID", insertable = false, updatable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "MaTour", insertable = false, updatable = false)
    private Tour tour;

    // Constructors
    public ChiTietWishlist() {}
}
