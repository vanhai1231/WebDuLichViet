package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.TaiKhoanService;
import com.hutech.DAMH.service.TourService;
import com.hutech.DAMH.service.WishlistService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private TourService tourService;

    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("/addTour")
    public ResponseEntity<Void> addTourToWishlist(@RequestBody WishlistRequest request) {
        try {
            Tour tour = tourService.getTourByMaTour(request.getMaTour());
            wishlistService.addTourToWishlist(request.getWishlistID(), tour);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).build(); // Conflict
        }
    }

    @DeleteMapping("/removeTour")
    public ResponseEntity<Void> removeTourFromWishlist(@RequestBody WishlistRequest request) {
        Tour tour = tourService.getTourByMaTour(request.getMaTour());
        wishlistService.removeTourFromWishlist(request.getWishlistID(), tour);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/id")
    public int getWishlistId(Authentication authentication) throws Exception {
        int userId = getCurrentUserID(authentication);
        return wishlistService.getOrCreateWishlistId(userId);
    }


    @Getter
    @Setter
    public static class WishlistRequest {
        private String maTour;
        private int wishlistID;
    }

    private int getCurrentUserID(Authentication authentication) throws Exception {
        String username = null;

        if (authentication.getPrincipal() instanceof UserDetails) {
            username = ((UserDetails) authentication.getPrincipal()).getUsername();
        } else if (authentication.getPrincipal() instanceof OAuth2User) {
            username = ((OAuth2User) authentication.getPrincipal()).getAttribute("email");
        } else {
            username = authentication.getName();
        }

        if (username == null) {
            throw new Exception("Cannot find username from authentication");
        }

        String finalUsername = username;
        TaiKhoan taiKhoan = taiKhoanService.findByTenTK(username)
                .orElseThrow(() -> new Exception("Không tìm thấy tài khoản với tên: " + finalUsername));
        return taiKhoan.getID();
    }
}
