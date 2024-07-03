package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.Phong;
import com.hutech.DAMH.repository.LoaiPhongRepository;
import com.hutech.DAMH.service.KhachSanService;
import com.hutech.DAMH.service.LoaiPhongService;
import com.hutech.DAMH.service.PhongService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class PhongAdminController {

    private final PhongService phongService;
    private final KhachSanService khachSanService;
    private final LoaiPhongService loaiPhongService;
    @GetMapping("/DanhSachPhong")
    public String ShowPhong(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("phongs",phongService.getAllPhong());
        return "/Admin/phong-list";

    }
    @GetMapping("/ThemPhong")
    public String showAddFormP(Model model) {
        model.addAttribute("phong", new Phong());
        model.addAttribute("khachSans", khachSanService.getALLKhachSan());
        model.addAttribute("loaiPhongs", loaiPhongService.getAllLoaiPhong());
        return "/Admin/add-phong";
    }

    @PostMapping("/ThemPhong")
    public String addProductP(@ModelAttribute("phong") Phong phong) {
        try {
            phongService.addPhong(phong);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemPhong?error";
        }
        return "redirect:/Admin/DanhSachPhong";


    }
    @GetMapping("/SuaPhong/{id}")
    public String showEditFormHD(@PathVariable("id") String maPhong, Model model) {
        Phong phong = phongService.getPhongId(maPhong)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Phong Id:" + maPhong));
        model.addAttribute("phong", phong);
        model.addAttribute("khachSans", khachSanService.getALLKhachSan());
        model.addAttribute("loaiPhongs", loaiPhongService.getAllLoaiPhong());
        return "/Admin/update-phong";
    }
    // Process the form for updating a product
    @PostMapping("/CapNhatPhong/{id}")
    public String updateP(@PathVariable("id") String maPhong, @ModelAttribute("phong") Phong phong) {


        try {
            phong.setMaPhong(maPhong);
            phongService.updatePhong(phong);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/SuaPhong/" + phong.getMaPhong() + "?error";
        }
        return "redirect:/Admin/DanhSachPhong";
    }

    @GetMapping("/XoaPhong/{id}")
    public String deleteP(@PathVariable("id") String maPhong) {
        try {
            phongService.deletePById(maPhong);
        } catch (DataIntegrityViolationException e) {
            // Xử lý khi xóa bị dính khóa ngoại
            // Chuyển hướng đến trang danh sách tour với thông báo lỗi
            return "redirect:/LoiXoaPhong";
        }

        // Chuyển hướng người dùng đến trang danh sách tour sau khi xóa thành công hoặc xử lý lỗi
        return "redirect:/Admin/DanhSachPhong";
    }
}
