package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.LoaiTour;
import com.hutech.DAMH.service.LoaiTourService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class LoaiTourAdminController {
    private final LoaiTourService loaiTourService;
    @GetMapping("DanhSachLoaiTour")
    public String showLoaiTourList(Model model, HttpServletRequest request) {
        // Kiểm tra session
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("loaitours", loaiTourService.getAllLoaiTour());
        return "/Admin/loaitours-list";
    }
    @GetMapping("/ThemLoaiTour")
    public String showAddFormLT(Model model) {
        model.addAttribute("loaitour", new LoaiTour());

        return "/Admin/add-loaitour";
    }

    @PostMapping("/ThemLT")
    public String addLT(@ModelAttribute("khachsan") LoaiTour loaiTour) {
        try {
            loaiTourService.addLT(loaiTour);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemLoaiTour?error";
        }
        return "redirect:/Admin/DanhSachLoaiTour";


    }
    @GetMapping("/SuaLoaiTour/{id}")
    public String showEditFormHD(@PathVariable("id") String maLoaiTour, Model model) {
        LoaiTour loaiTour = loaiTourService.getMaLoaiTour(maLoaiTour)
                .orElseThrow(() -> new IllegalArgumentException("Invalid LoaiTour Id:" + maLoaiTour));
        model.addAttribute("loaitour", loaiTour);

        return "/Admin/update-loaitour";
    }
    // Process the form for updating a product
    @PostMapping("/UpdateLoaiTour/{id}")
    public String updateKS(@PathVariable("id") String maLoaiTour, @ModelAttribute("loaitour") LoaiTour loaiTour) {


        try {
            loaiTour.setMaLoaiTour(maLoaiTour);
            loaiTourService.updateLT(loaiTour);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/SuaLoaiTour/" + loaiTour.getLoaiTour() + "?error";
        }
        return "redirect:/Admin/DanhSachLoaiTour";
    }
    @GetMapping("/XoaLoaiTour/{id}")
    public String deleteLoaiTour(@PathVariable("id") String maLoaiTour) {
        loaiTourService.deleteLTById(maLoaiTour);
        return "redirect:/Admin/DanhSachLoaiTour";
    }
}
