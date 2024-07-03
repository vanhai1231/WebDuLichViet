package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.LoaiPhong;
import com.hutech.DAMH.model.LoaiTour;
import com.hutech.DAMH.service.LoaiPhongService;
import com.hutech.DAMH.service.LoaiTourService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class LoaiPhongAdminController {
    private final LoaiPhongService loaiPhongService;
    @GetMapping("DanhSachLoaiPhong")
    public String showLoaiLPList(Model model, HttpServletRequest request) {
        // Kiểm tra session
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("loaiphongs", loaiPhongService.getAllLoaiPhong());
        return "/Admin/loaiphong-list";
    }
    @GetMapping("/ThemLoaiPhong")
    public String showAddFormLT(Model model) {
        model.addAttribute("loaiphong", new LoaiPhong());

        return "/Admin/add-loaiphong";
    }

    @PostMapping("/ThemLoaiPhong")
    public String addLT(@ModelAttribute("loaiphong") LoaiPhong loaiPhong) {
        try {
            loaiPhongService.addLP(loaiPhong);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemLoaiPhong?error";
        }
        return "redirect:/Admin/DanhSachLoaiPhong";


    }
    @GetMapping("/SuaLoaiPhong/{id}")
    public String showEditFormHD(@PathVariable("id") String maLoai, Model model) {
        LoaiPhong loaiPhong = loaiPhongService.getMaLoaiPhong(maLoai)
                .orElseThrow(() -> new IllegalArgumentException("Invalid LoaiTour Id:" + maLoai));
        model.addAttribute("loaiphong", loaiPhong);

        return "/Admin/update-loaiphong";
    }
    // Process the form for updating a product
    @PostMapping("/CapNhatLoaiPhong/{id}")
    public String updateLP(@PathVariable("id") String maLoai, @ModelAttribute("loaiphong") LoaiPhong loaiPhong) {


        try {
            loaiPhong.setMaLoai(maLoai);
            loaiPhongService.updateLP(loaiPhong);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/SuaLoaiPhong/" + loaiPhong.getMaLoai() + "?error";
        }
        return "redirect:/Admin/DanhSachLoaiPhong";
    }





    @GetMapping("/XoaLoaiPhong/{id}")
    public String deleteLoaiTour(@PathVariable("id") String maLoai) {
        try {
            loaiPhongService.deleteLPById(maLoai);
        } catch (DataIntegrityViolationException e) {
            // Xử lý khi xóa bị dính khóa ngoại
            // Chuyển hướng đến trang danh sách tour với thông báo lỗi
            return "redirect:/LoiXoaLoaiPhong";
        }

        // Chuyển hướng người dùng đến trang danh sách tour sau khi xóa thành công hoặc xử lý lỗi
        return "redirect:/Admin/DanhSachLoaiPhong";
    }
}
