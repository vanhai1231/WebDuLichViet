package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.*;
import com.hutech.DAMH.service.ImagesService;
import com.hutech.DAMH.service.LoaiTourService;
import com.hutech.DAMH.service.PhuongTienService;
import com.hutech.DAMH.service.TourService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    @GetMapping("/Home")
    public String showProductList(Model model, HttpSession session) {

        // Lấy danh sách các tour và thêm vào model
        List<Tour> tours = tourService.getAllTours();
        for (Tour tour : tours) {
            List<String> imageUrls = imagesService.getImagesByMaTour(tour.getMaTour());
            if (!imageUrls.isEmpty()) {
                tour.setMainImageUrl(imageUrls.get(0)); // Hình ảnh chính
                tour.setSecondaryImageUrl(imageUrls.get(1)); // Các hình ảnh khác
            }
        }
        model.addAttribute("tours", tours);


        return "/index/index";
    }

    @GetMapping("/Blog")
    public String showBlog(Model model) {

        if (!model.containsAttribute("taiKhoan")) {
            model.addAttribute("taiKhoan", new TaiKhoan());
        }

        return "index/Blog";
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

        Page<Tour> tourPage = tourService.getProductsbyPage(page, size);
        List<Tour> tours = tourPage.getContent();
        for (Tour tour : tours) {
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

}
