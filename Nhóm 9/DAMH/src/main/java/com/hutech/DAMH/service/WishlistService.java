package com.hutech.DAMH.service;

import com.hutech.DAMH.model.ChiTietWishlist;
import com.hutech.DAMH.model.ChiTietWishlistId;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.model.Wishlist;
import com.hutech.DAMH.repository.ChiTietWishlistRepository;
import com.hutech.DAMH.repository.TaiKhoanRepository;
import com.hutech.DAMH.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ChiTietWishlistRepository chiTietWishlistRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    public int getOrCreateWishlistId(int userId) {
        TaiKhoan taiKhoan = taiKhoanRepository.findByID(userId);
        Wishlist wishlist = wishlistRepository.findByTaiKhoan(taiKhoan);
        if (wishlist == null) {
            wishlist = new Wishlist();
            wishlist.setTaiKhoan(taiKhoan);
            wishlist = wishlistRepository.save(wishlist);
        }
        return wishlist.getWishlistID();
    }

    public void addTourToWishlist(int wishlistId, Tour tour) {
        ChiTietWishlistId id = new ChiTietWishlistId(tour.getMaTour(), wishlistId);
        Optional<ChiTietWishlist> chiTietWishlistOptional = chiTietWishlistRepository.findById(id);

        if (chiTietWishlistOptional.isPresent()) {
            // Tour is already in wishlist, handle appropriately if needed
            throw new IllegalStateException("Tour is already in wishlist");
        } else {
            ChiTietWishlist chiTietWishlist = new ChiTietWishlist();
            chiTietWishlist.setMaTour(tour.getMaTour());
            chiTietWishlist.setWishlistID(wishlistId);
            chiTietWishlist.setTour(tour);
            chiTietWishlistRepository.save(chiTietWishlist);
        }
    }

    public void removeTourFromWishlist(int wishlistId, Tour tour) {
        ChiTietWishlistId id = new ChiTietWishlistId(tour.getMaTour(), wishlistId);
        chiTietWishlistRepository.deleteById(id);
    }
}
