package com.hutech.DAMH.service;

import com.hutech.DAMH.model.ChiTietWishlist;
import com.hutech.DAMH.repository.ChiTietWishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiTietWishlistService {
    @Autowired
    private ChiTietWishlistRepository chiTietWishlistRepository;

    public List<ChiTietWishlist> findByWishlistID(int wishlistID) {
        return chiTietWishlistRepository.findByWishlistID(wishlistID);
    }
}
