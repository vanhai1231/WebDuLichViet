package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class AdminController {
    private final TaiKhoanService taiKhoanService;
    private final HoaDonServiceImpl hoaDonService;
    private final KhachSanService khachSanService;
    private final TourService tourService;

    @GetMapping("/Home")
    public String showAdmin(Model model,HttpServletRequest request) {

        HttpSession session = request.getSession(false); // kiểm tra session
        Object usernameAttribute = session.getAttribute("username");
        // Kiểm tra xem đã đăng nhập hay chưa
        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }
        // lấy doanh thu theo tháng
        Map<String, BigDecimal> revenueByMonth = hoaDonService.getRevenueByMonth();

        // dữ liệu cho  Chart.js
        List<String> months = new ArrayList<>(revenueByMonth.keySet());
        List<BigDecimal> revenues = new ArrayList<>(revenueByMonth.values());

        // thêm dữ liệu vào model
        model.addAttribute("months", months);
        model.addAttribute("revenues", revenues);
        //hiện giá tiền
        BigDecimal totalTongTien = hoaDonService.getTotalTongTien();
        //chỉnh kiểu tiền
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vnFormat = NumberFormat.getInstance(localeVN);
        String formattedPrice = vnFormat.format(totalTongTien) + " VNĐ";
        model.addAttribute("formattedPrice", formattedPrice);
        //Số lượng đơn hàng
        long numberOfInvoices = hoaDonService.countAllHoaDon();
        model.addAttribute("numberOfInvoices", numberOfInvoices);
        //Số Tài Khoản
        long numberAccount = taiKhoanService.countUserAccounts();
        model.addAttribute("numberAccount", numberAccount);
        //Số Khách Sạn
        long numberHotel = khachSanService.countAllKhachSan();
        model.addAttribute("numberHotel", numberHotel);
        //Số Tour
        long numberTour = tourService.countAllTour();
        model.addAttribute("numberTour", numberTour);
        if (usernameAttribute instanceof String) {
            String username = (String) usernameAttribute;
            model.addAttribute("username", username);
        }

        else {
            // Handle the case where the attribute is not a String
            System.err.println("Expected a String but found: " + usernameAttribute.getClass().getName());
            model.addAttribute("username", ""); // Or some default value or error handling
        }
        return "/Admin/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/Admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {

        //Option là đối tượng có thể null
        //tìm tenTK để đăng nhập
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByTenTK(username);
        if (optionalTaiKhoan.isEmpty()) {

            return "redirect:/Admin/login?error";
        }
        //lấy đối tượng nếu tìm kiếm được
        TaiKhoan taiKhoan = optionalTaiKhoan.get();
        //kiêểm tra password
        if (!taiKhoanService.passwordsMatch(password, taiKhoan.getPassword())) {
            return "redirect:/Admin/login?error";
        }
        //kiểm tra quyền của đối tượng đăng nhập nếu là ADMIN và EMPLOYEE thì đăng nhập đc
        Collection<? extends GrantedAuthority> authorities = taiKhoan.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (Objects.equals(authority.getAuthority(), "ADMIN") || Objects.equals(authority.getAuthority(), "EMPLOYEE")) {
                model.addAttribute("username", authority.getAuthority());
                // Store roles or username in session attributes if needed
                return "redirect:/Admin/Home"; // Redirect to admin home page after successful login
            }
        }

        return "redirect:/Admin/login?error";
    }

    @GetMapping("/logoutAdmin")
    public String logout(HttpServletRequest request, HttpSession session, ModelMap model) {
        // Huỷ phiên đăng nhập (invalidate session)
        session.invalidate();

        // Xóa ngữ cảnh bảo mật (clear security context)
        SecurityContextHolder.clearContext();

        // Xóa thuộc tính "username" khỏi model
        model.remove("username");

        // Redirect to login page
        return "redirect:/Admin/login";
    }

}
