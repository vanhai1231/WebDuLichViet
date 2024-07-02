package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.*;
import com.hutech.DAMH.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class TourAdminController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TinhService tinhService;
    @Autowired
    private LoaiTourService loaiTourService;
    @Autowired
    private PhongService phongService;
    @Autowired
    private PhuongTienService phuongTienService;
    @Autowired
    private KhuyenMaiService khuyenMaiService;
    @Autowired
    private ChiTietKhuyenMaiService chiTietKhuyenMaiService;
    @GetMapping("DanhSachTour")
    public String showTourList(Model model, HttpServletRequest request) {
        // Kiểm tra session
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("tours", tourService.getAllToursAdmin());
        return "/Admin/tours-list";
    }

    @GetMapping("/ThemTour")
    public String showAddTourForm(Model model) {
        model.addAttribute("tour", new Tour());
        model.addAttribute("tinhs", tinhService.getAllTinh());
        model.addAttribute("loaiTours", loaiTourService.getAllLoaiTour());
        model.addAttribute("phongs", phongService.getAllPhong());
        model.addAttribute("phuongTiens", phuongTienService.getAllPhuongTien());
        return "/Admin/add-tour-form";
    }

    // Handle form submission to add a new tour
    @PostMapping("/AddTour")
    public String addTour(@ModelAttribute("tour") Tour tour,
                          @RequestParam("mainImageFile") MultipartFile mainImageFile,
                          @RequestParam("secondaryImageFile") MultipartFile secondaryImageFile) {
        try {
            String mainImageUrl = tourService.uploadImage(mainImageFile);
            String secondaryImageUrl = tourService.uploadImage(secondaryImageFile);
            tourService.addTour(tour, mainImageUrl, secondaryImageUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemTour?error";
        }
        return "redirect:/Admin/DanhSachTour";
    }
    @GetMapping("/edit/{id}")
    public String showEditTourForm(@PathVariable("id") String maTour, Model model) {
        Tour tour = tourService.getTourByMaTour(maTour);


        // Lấy danh sách hình ảnh từ Tour
        List<Images> imagesList = tourService.getImagesByMaTour(maTour);

        // Nếu có hình ảnh, lấy hình ảnh đầu tiên làm hình ảnh chính
        if (imagesList != null && !imagesList.isEmpty()) {
            if (!imagesList.isEmpty()) {
                String mainImageUrl = imagesList.get(0).getHinhAnh().getImg();
                tour.setMainImageUrl(mainImageUrl);
            }
            if (imagesList.size() > 1) {
                String secondaryImageUrl = imagesList.get(1).getHinhAnh().getImg();
                tour.setSecondaryImageUrl(secondaryImageUrl);
            }
        }
        model.addAttribute("tour", tour);
        model.addAttribute("tinhs", tinhService.getAllTinh());
        model.addAttribute("loaiTours", loaiTourService.getAllLoaiTour());
        model.addAttribute("phongs", phongService.getAllPhong());
        model.addAttribute("phuongTiens", phuongTienService.getAllPhuongTien());
        return "/Admin/update-tour-form";
    }

    @PostMapping("/UpdateTour/{id}")
    public String updateTour(@PathVariable("id") String maTour, @ModelAttribute("tour") Tour tour,
                             @RequestParam(value = "mainImageFile", required = false) MultipartFile mainImageFile,
                             @RequestParam(value = "secondaryImageFile", required = false) MultipartFile secondaryImageFile) throws IOException {
        try {
            tour.setMaTour(maTour);

            // Update main image if provided
            if (mainImageFile != null && !mainImageFile.isEmpty()) {
                String mainImageUrl = tourService.uploadImage(mainImageFile);
                tourService.updateMainImageForTour(tour, mainImageUrl);
            }

            // Update secondary image if provided
            if (secondaryImageFile != null && !secondaryImageFile.isEmpty()) {
                String secondaryImageUrl = tourService.uploadImage(secondaryImageFile);
                tourService.updateSecondaryImageForTour(tour, secondaryImageUrl);
            }

            // Update other tour details
            tourService.updateTour(tour,mainImageFile,secondaryImageFile);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/edit/" + tour.getMaTour() + "?error";
        }
        return "redirect:/Admin/DanhSachTour";
    }
    @GetMapping("/delete/{id}")
    public String deleteTour(@PathVariable("id") String maTour) {
        tourService.deleteTour(maTour);
        return "redirect:/Admin/DanhSachTour";
    }

    @GetMapping("/KhuyenMai")
    public String showKhuyenMaiForm(Model model) {
        List<Tour> tours = tourService.getAllTours();
        model.addAttribute("tours", tours);
        return "/Admin/khuyenmai";
    }

    @PostMapping("/KhuyenMai")
    public String createKhuyenMai(@RequestParam("tenKM") String tenKM,
                                  @RequestParam("phanTramKM") int phanTramKM,
                                  @RequestParam("ngayBatDau") String ngayBatDau,
                                  @RequestParam("ngayKetThuc") String ngayKetThuc,
                                  @RequestParam("maTour") String maTour) {
        KhuyenMai khuyenMai = new KhuyenMai();
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String maKM = "KM" + timeStamp.substring(timeStamp.length() - 8);
        khuyenMai.setMaKM(maKM);

        khuyenMai.setTenKM(tenKM);
        khuyenMai.setPhanTramKM(phanTramKM);
        khuyenMai.setNgayBatDau(Date.valueOf(ngayBatDau));
        khuyenMai.setNgayKetThuc(Date.valueOf(ngayKetThuc));

        khuyenMaiService.save(khuyenMai);

        ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai();
        chiTietKhuyenMai.setMaTour(maTour);
        chiTietKhuyenMai.setMaKM(khuyenMai.getMaKM());

        chiTietKhuyenMaiService.save(chiTietKhuyenMai);

        return "redirect:/Admin/KhuyenMai";
    }

    @GetMapping("/DanhSachKhuyenMai")
    public String danhSachKhuyenMai(Model model) {
        List<KhuyenMai> danhSachKhuyenMai = khuyenMaiService.findAll();

        List<KhuyenMaiDTO> khuyenMaiDTOs = new ArrayList<>();
        for (KhuyenMai km : danhSachKhuyenMai) {
            List<ChiTietKhuyenMai> chiTietKhuyenMais = chiTietKhuyenMaiService.findByMaKM(km.getMaKM());
            for (ChiTietKhuyenMai ctkm : chiTietKhuyenMais) {
                String tenTour = ctkm.getTour().getTenTour();
                KhuyenMaiDTO dto = new KhuyenMaiDTO(km, tenTour);
                khuyenMaiDTOs.add(dto);
            }
        }

        model.addAttribute("danhSachKhuyenMai", khuyenMaiDTOs);
        return "Admin/DanhSachKhuyenMai";
    }
}
