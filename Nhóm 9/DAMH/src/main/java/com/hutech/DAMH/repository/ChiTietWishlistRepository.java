package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.ChiTietWishlist;
import com.hutech.DAMH.model.ChiTietWishlistId;
import com.hutech.DAMH.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietWishlistRepository extends JpaRepository<ChiTietWishlist, ChiTietWishlistId> {
    List<ChiTietWishlist> findByWishlistID(int wishlistID);
}
