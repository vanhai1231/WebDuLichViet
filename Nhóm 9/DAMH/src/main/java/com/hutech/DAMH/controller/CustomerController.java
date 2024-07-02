package com.hutech.DAMH.controller;

import com.hutech.DAMH.CustomUserDetails;
import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.service.HoaDonService;
import com.hutech.DAMH.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final TaiKhoanService taiKhoanService;


    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    public CustomerController(TaiKhoanService taiKhoanService, HoaDonService hoaDonService) {
        this.taiKhoanService = taiKhoanService;
        this.hoaDonService = hoaDonService;
    }

    @GetMapping("/HoaDon")
    public String viewInvoices(Model model, Authentication authentication) throws Exception {
        List<HoaDon> invoices = new ArrayList<>();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof OAuth2User) {
                // OAuth2 authentication logic
                OAuth2User oauth2User = (OAuth2User) principal;
                String email = oauth2User.getAttribute("email");
                TaiKhoan taiKhoan = taiKhoanService.findByEmail(email)
                        .orElseThrow(() -> new Exception("Không tìm thấy tài khoản với email: " + email));
                invoices = hoaDonService.findById(taiKhoan.getID());
            } else if (principal instanceof UserDetails) {
                // Regular UserDetails authentication logic
                String username = ((UserDetails) principal).getUsername();
                TaiKhoan taiKhoan = taiKhoanService.findByTenTK(username)
                        .orElseThrow(() -> new Exception("Không tìm thấy tài khoản với tên: " + username));
                invoices = hoaDonService.findById(taiKhoan.getID());
            } else {
                throw new IllegalStateException("Unsupported principal type: " + principal.getClass().getName());
            }
        }

        model.addAttribute("invoices", invoices);
        return "Customer/index";
    }


    private String getCurrentUsername(OAuth2AuthenticationToken authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            OAuth2User principal = authentication.getPrincipal();
            return principal.getAttribute("email");
        }
        return null;
    }

}
