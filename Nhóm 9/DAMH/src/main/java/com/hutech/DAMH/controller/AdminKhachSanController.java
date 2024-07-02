package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.DanhGiaService;
import com.hutech.DAMH.service.KhachSanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class AdminKhachSanController {
    private final KhachSanService khachSanService;
    private final DanhGiaService danhGiaService;
    @GetMapping("/DanhSachKhachSan")
    public String ShowKhachSan(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("khachsans",khachSanService.getALLKhachSan());
        return "/Admin/hotel-list";

    }
    @GetMapping("/ThemKhachSan")
    public String showAddFormKS(Model model) {
        model.addAttribute("khachsan", new KhachSan());
        model.addAttribute("danhgia", danhGiaService.getAllDanhGia());
        return "/Admin/add-hotel";
    }

    @PostMapping("/ThemKS")
    public String addProduct(@ModelAttribute("khachsan") KhachSan khachsan) {
        try {
            khachSanService.addKhachSan(khachsan);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemKhachSan?error";
        }
        return "redirect:/Admin/DanhSachKhachSan";


    }
    @GetMapping("/editKhachSan/{id}")
    public String showEditFormHD(@PathVariable("id") String maKS, Model model) {
        KhachSan khachsan = khachSanService.getKhachSanId(maKS)
                .orElseThrow(() -> new IllegalArgumentException("Invalid hoadon Id:" + maKS));
        model.addAttribute("khachsan", khachsan);

        return "/Admin/update-hotel";
    }
    // Process the form for updating a product
    @PostMapping("/SuaKhachSan/{id}")
    public String updateKS(@PathVariable("id") String maKS, @ModelAttribute("khachsan") KhachSan khachsan) {


        try {
            khachsan.setMaKS(maKS);
            khachSanService.updateKhachSan(khachsan);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/editKhachSan/" + khachsan.getMaKS() + "?error";
        }
        return "redirect:/Admin/DanhSachKhachSan";
    }
    @GetMapping("/XoaKhachSan/{id}")
    public String deleteKS(@PathVariable("id") String maKS) {
        khachSanService.deleteKSById(maKS);
        return "redirect:/Admin/DanhSachKhachSan";
    }
}
