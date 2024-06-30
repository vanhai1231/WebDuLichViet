package com.hutech.DAMH.model;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietWishlistId implements Serializable {
    private String maTour;
    private int wishlistID;

    // Constructors, getters, setters, equals, and hashCode
    public ChiTietWishlistId() {}

    public ChiTietWishlistId(String maTour, int wishlistID) {
        this.maTour = maTour;
        this.wishlistID = wishlistID;
    }

    // Getters and Setters
    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietWishlistId that = (ChiTietWishlistId) o;
        return wishlistID == that.wishlistID && Objects.equals(maTour, that.maTour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maTour, wishlistID);
    }
}
