package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.service.HoaDonServiceImpl;
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
public class AdminHoaDonController {
    private HoaDonServiceImpl hoaDonService;
    @GetMapping("/DanhSachHoaDon")
    public String showHoaDon(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        model.addAttribute("hoadons", hoaDonService.getAllBills());
        return "/Admin/bill-admin";
    }



    @GetMapping("/editHoaDon/{id}")
    public String showEditFormHD(@PathVariable("id") String maHD, Model model) {
        HoaDon hoadon = hoaDonService.getHoaDonId(maHD)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hoadon Id:" + maHD));
        model.addAttribute("hoadon", hoadon);

        return "/Admin/update-bill";
    }
    // Process the form for updating a product
    @PostMapping("/SuaHoaDon/{id}")
    public String updateHoaDon(@PathVariable("id") String maHD, @ModelAttribute("hoadon") HoaDon hoadon) {


        try {
            hoadon.setMaHD(maHD);
            hoaDonService.updateHoaDon(hoadon);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/editHoaDon/" + hoadon.getMaHD() + "?error";
        }
        return "redirect:/Admin/DanhSachHoaDon";
    }
}
