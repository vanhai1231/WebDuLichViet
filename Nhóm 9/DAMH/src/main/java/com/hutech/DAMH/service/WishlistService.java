package com.hutech.DAMH.service;

import com.hutech.DAMH.model.*;
import com.hutech.DAMH.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ChiTietWishlistRepository chiTietWishlistRepository;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private TourRepository tourRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private TourService tourService;

    public List<Images> getImagesByMaTour(String maTour) {
        return imagesRepository.findByMaTour(maTour);
    }


    public Optional<Wishlist> findByTaiKhoan(TaiKhoan taiKhoan) {
        return Optional.ofNullable(wishlistRepository.findByTaiKhoan(taiKhoan));
    }
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
