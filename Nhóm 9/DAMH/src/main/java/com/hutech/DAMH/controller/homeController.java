package com.hutech.DAMH.controller;

import com.hutech.DAMH.CustomUserDetails;
import com.hutech.DAMH.model.*;
import com.hutech.DAMH.service.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedWriter;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
//@SessionAttributes({"taiKhoan", "userId"})
@RequestMapping("/DuLichViet")
public class homeController {

    @Autowired
    private TourService tourService;
    @Autowired
    private ImagesService imagesService; // Thêm dòng này
    @Autowired
    private LoaiTourService loaiTourService;
    @Autowired
    private PhuongTienService phuongTienService;
    @Autowired
    private KhuyenMaiService khuyenMaiService;
    @Autowired
    private ChiTietKhuyenMaiService chiTietKhuyenMaiService;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ChiTietWishlistService chiTietWishlistService;

    @GetMapping("/Home")
    public String showProductList(Model model, HttpSession session) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

        // Lấy danh sách các tour và thêm vào model
        List<Tour> tours = tourService.getAllTours();
        for (Tour tour : tours) {
            List<String> imageUrls = imagesService.getImagesByMaTour(tour.getMaTour());
            if (!imageUrls.isEmpty()) {
                tour.setMainImageUrl(imageUrls.get(0)); // Hình ảnh chính
                tour.setSecondaryImageUrl(imageUrls.get(1)); // Các hình ảnh khác
            }
            String formattedPrice = numberFormat.format(tour.getGiaTour());
            tour.setFormattedGiaTour(formattedPrice + "VNĐ");

            // Kiểm tra xem tour có trong bảng ChiTietKhuyenMai không
            ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findByMaTour(tour.getMaTour());
            if (chiTietKhuyenMai != null) {
                // Lấy thông tin chi tiết khuyến mãi
                Optional<KhuyenMai> khuyenMai = khuyenMaiService.findByMaKM(chiTietKhuyenMai.getMaKM());
                if (khuyenMai.isPresent() && isWithinPromotionPeriod(khuyenMai.orElse(null))) {
                    // Nếu đang trong thời gian khuyến mãi, thêm thông tin khuyến mãi vào tour
                    tour.setPromotionActive(true);
                    tour.setPhanTramGiam(khuyenMai.get().getPhanTramKM());
                    tour.setNgayBatDauKM(khuyenMai.get().getNgayBatDau());
                    tour.setNgayKetThucKM(khuyenMai.get().getNgayKetThuc());
                }
            }
        }

        model.addAttribute("tours", tours);
        return "/index/index";
    }

    @GetMapping("/About")
    public String showAbout(Model model) {
        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        return "index/About";
    }

    @GetMapping("/TourDetail/{maTour}")
    public String showTourDetail(@PathVariable("maTour") String maTour, Model model) {
        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        // Lấy thông tin chi tiết của tour từ maTour
        Tour tour = tourService.getTourByMaTour(maTour);

        // Lấy danh sách các URL hình ảnh của tour
        List<String> imageUrls = imagesService.getImagesByMaTour(maTour);
        if (!imageUrls.isEmpty()) {
            tour.setMainImageUrl(imageUrls.get(0)); // Hình ảnh chính
            if (imageUrls.size() > 1) {
                tour.setSecondaryImageUrl(imageUrls.get(1)); // Hình ảnh phụ
            }
            tour.setOtherImageUrls(imageUrls.subList(1, imageUrls.size())); // Các hình ảnh khác
        }
        // Kiểm tra và áp dụng khuyến mãi nếu có
        ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findByMaTour(maTour);
        if (chiTietKhuyenMai != null) {
            Optional<KhuyenMai> khuyenMai = khuyenMaiService.findByMaKM(chiTietKhuyenMai.getMaKM());
            if (khuyenMai.isPresent() && isWithinPromotionPeriod(khuyenMai.orElse(null))) {
                double giaKhuyenMai = calculatePromotionalPrice(tour.getGiaTour(), khuyenMai.get().getPhanTramKM());
                tour.setGiaKhuyenMai(giaKhuyenMai);
                tour.setPromotionActive(true);
                tour.setPhanTramGiam(khuyenMai.get().getPhanTramKM());
                tour.setNgayBatDauKM(khuyenMai.get().getNgayBatDau());
                tour.setNgayKetThucKM(khuyenMai.get().getNgayKetThuc());
            }
        }
        // Thêm tour và danh sách hình ảnh vào model
        model.addAttribute("tour", tour);
        return "index/TourDetail"; // Trả về view hiển thị chi tiết tour
    }

    @GetMapping("/Tour")
    public String showTour(@RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "4") int size, Model model) {

        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        Page<Tour> tourPage = tourService.getProductsByPage(page, size);
        List<Tour> tours = tourPage.getContent();

        StringBuilder tourInfoBuilder = new StringBuilder();

        // Lặp qua danh sách tour để kiểm tra thông tin khuyến mãi
        for (Tour tour : tours) {
            // Kiểm tra xem tour có trong bảng ChiTietKhuyenMai không
            ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findByMaTour(tour.getMaTour());
            if (chiTietKhuyenMai != null) {
                // Lấy thông tin chi tiết khuyến mãi
                Optional<KhuyenMai> khuyenMai = khuyenMaiService.findByMaKM(chiTietKhuyenMai.getMaKM());
                if (khuyenMai.isPresent() && isWithinPromotionPeriod(khuyenMai.orElse(null))) {
                    // Nếu đang trong thời gian khuyến mãi, thêm thông tin khuyến mãi vào tour
                    tour.setPromotionActive(true);
                    tour.setPhanTramGiam(khuyenMai.get().getPhanTramKM());
                    tour.setNgayBatDauKM(khuyenMai.get().getNgayBatDau());
                    tour.setNgayKetThucKM(khuyenMai.get().getNgayKetThuc());
                }
            }

            // Lấy danh sách hình ảnh của tour
            List<String> imageUrls = imagesService.getImagesByMaTour(tour.getMaTour());
            if (!imageUrls.isEmpty()) {
                tour.setMainImageUrl(imageUrls.get(0));
                if (imageUrls.size() > 1) {
                    tour.setSecondaryImageUrl(imageUrls.get(1));
                }
            }
        }

        model.addAttribute("tours", tours);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tourPage.getTotalPages());
        model.addAttribute("pageNumbers", IntStream.range(0, tourPage.getTotalPages()).boxed().collect(Collectors.toList()));

        List<LoaiTour> loaiTours = loaiTourService.getAllLoaiTour();
        List<PhuongTien> phuongTiens = phuongTienService.getAllPhuongTien();
        model.addAttribute("loaiTours", loaiTours);
        model.addAttribute("phuongTiens", phuongTiens);

        return "index/Tour";
    }

    @GetMapping("/Wishlist")
    public String showWishlist(Model model, HttpSession session, Authentication authentication) {
        // Ensure taiKhoan attribute is added to the model if not present
        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        int userID = customUserDetails.getUserId();
        int wishlistId = wishlistService.getOrCreateWishlistId(userID);
        // Use wishlistId to fetch chiTietWishlistList
        List<ChiTietWishlist> chiTietWishlistList = chiTietWishlistService.findByWishlistID(wishlistId);
        List<Tour> tours = chiTietWishlistList.stream()
                .map(ChiTietWishlist::getTour)
                .collect(Collectors.toList());

        // Process tours to set image URLs and promotions
        for (Tour tour : tours) {
            List<String> imageUrls = imagesService.getImagesByMaTour(tour.getMaTour());
            if (!imageUrls.isEmpty()) {
                tour.setMainImageUrl(imageUrls.get(0));
                if (imageUrls.size() > 1) {
                    tour.setSecondaryImageUrl(imageUrls.get(1));
                }
            }

            ChiTietKhuyenMai chiTietKhuyenMai = chiTietKhuyenMaiService.findByMaTour(tour.getMaTour());
            if (chiTietKhuyenMai != null) {
                Optional<KhuyenMai> khuyenMai = khuyenMaiService.findByMaKM(chiTietKhuyenMai.getMaKM());
                if (khuyenMai.isPresent() && isWithinPromotionPeriod(khuyenMai.get())) {
                    tour.setPromotionActive(true);
                    tour.setPhanTramGiam(khuyenMai.get().getPhanTramKM());
                    tour.setNgayBatDauKM(khuyenMai.get().getNgayBatDau());
                    tour.setNgayKetThucKM(khuyenMai.get().getNgayKetThuc());
                }
            }
        }

        // Add tours to model
        model.addAttribute("tours", tours);
        // Return the view name
        return "index/Wishlist";
    }

    private boolean isWithinPromotionPeriod(KhuyenMai khuyenMai) {
        Date currentDate = new Date(System.currentTimeMillis());
        return currentDate.after(khuyenMai.getNgayBatDau()) && currentDate.before(khuyenMai.getNgayKetThuc());
    }

    private double calculatePromotionalPrice(double originalPrice, double discountPercent) {
        double discountAmount = (discountPercent / 100) * originalPrice;
        return originalPrice - discountAmount;
    }

    @GetMapping("/Contact")
    public String showContact(Model model) {

        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        return "index/Contact";
    }
}
