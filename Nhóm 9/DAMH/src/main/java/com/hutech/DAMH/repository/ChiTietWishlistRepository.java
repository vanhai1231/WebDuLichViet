package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.ChiTietWishlist;
import com.hutech.DAMH.model.ChiTietWishlistId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietWishlistRepository extends JpaRepository<ChiTietWishlist, ChiTietWishlistId> {
}
