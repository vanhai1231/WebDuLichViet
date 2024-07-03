package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.Destination;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.service.DestinationService;
import com.hutech.DAMH.service.TinhService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class DestinationAdminController {
    private final DestinationService destinationService;
    private final TinhService tinhService;
    @GetMapping("DanhSachDiemDen")
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
        model.addAttribute("diemdens", destinationService.getAllDestinations());
        return "/Admin/diemden-list";
    }
    @GetMapping("/ThemDiemDen")
    public String showAddFormLT(Model model) {
        model.addAttribute("DiemDen", new Destination());
        model.addAttribute("tinhs",tinhService.getAllTinh());
        return "/Admin/add-destination";
    }

    @PostMapping("/ThemDiemDen")
    public String addLT(@ModelAttribute("DiemDen") Destination destination, @RequestParam("hinhAnhFile") MultipartFile hinhAnhFile) {
        try {
            String hinhAnhUrl = destinationService.uploadImage(hinhAnhFile);
            destination.setHinhAnh(hinhAnhUrl);
            destinationService.addDD(destination);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemDiemDen?error";
        }
        return "redirect:/Admin/DanhSachDiemDen";


    }
    @GetMapping("/SuaDiemDen/{id}")
    public String showEditFormHD(@PathVariable("id") int id, Model model,
                                 @RequestParam(value = "hinhAnhFile", required = false) MultipartFile hinhAnhFile) {
        Destination destination = destinationService.getId(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid DiemDen Id:" + id));
        model.addAttribute("destination", destination);
        model.addAttribute("tinhs",tinhService.getAllTinh());
        return "/Admin/update-diemden";
    }
    // Process the form for updating a product
    @PostMapping("/CapNhatDiemDen/{id}")
    public String updateDD(@PathVariable("id") int id,
                           @ModelAttribute("destination") Destination destination,
                           @RequestParam(value = "hinhAnhFile", required = false) MultipartFile hinhAnhFile) {
        try {
            Destination existingDestination = destinationService.getId(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid DiemDen Id:" + id));

            // Handle the image upload if a new image file is provided
            if (hinhAnhFile != null && !hinhAnhFile.isEmpty()) {
                String hinhAnhUrl = destinationService.uploadImage(hinhAnhFile);
                destination.setHinhAnh(hinhAnhUrl);
                destinationService.addDD(destination);
            } else {
                // Keep the existing image URL if no new image is uploaded
                destination.setHinhAnh(existingDestination.getHinhAnh());
            }

            // Ensure the destination ID is set correctly
            destination.setId(id);
            destinationService.updateDD(destination);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/SuaDiemDen/" + destination.getId() + "?error";
        }
        return "redirect:/Admin/DanhSachDiemDen";
    }

    @GetMapping("/XoaDiemDen/{id}")
    public String deleteDiemDen(@PathVariable("id") int id) {
        try {
            destinationService.deleteDDById(id);
        } catch (DataIntegrityViolationException e) {
            // Xử lý khi xóa bị dính khóa ngoại
            // Chuyển hướng đến trang danh sách tour với thông báo lỗi
            return "redirect:/LoiXoaDiemDen";
        }

        // Chuyển hướng người dùng đến trang danh sách tour sau khi xóa thành công hoặc xử lý lỗi
        return "redirect:/Admin/DanhSachDiemDen";
    }
}
