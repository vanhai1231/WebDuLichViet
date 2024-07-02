package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Wishlist;
import com.hutech.DAMH.service.WishlistService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
    Wishlist findByTaiKhoan(TaiKhoan taiKhoan);
}
