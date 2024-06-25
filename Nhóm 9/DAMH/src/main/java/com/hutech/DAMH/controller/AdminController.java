package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin")
public class AdminController {
    private final TaiKhoanService taiKhoanService;



    @GetMapping("/Home")
    public String showAdmin(Model model,HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Check existing session
        Object usernameAttribute = session.getAttribute("username");
        // Kiểm tra xem đã đăng nhập hay chưa


        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login"; // Redirect to login if not logged in
        }



        if (usernameAttribute instanceof String) {
            String username = (String) usernameAttribute;
            model.addAttribute("username", username);
        } else {
            // Handle the case where the attribute is not a String
            // For example, if it's a List
            System.err.println("Expected a String but found: " + usernameAttribute.getClass().getName());
            model.addAttribute("username", ""); // Or some default value or error handling
        }
        return "/Admin/index";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/Admin/login"; // Assuming you have a login.html or login.jsp template
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {

        // Authenticate user against the database
        TaiKhoan taiKhoan = taiKhoanService.findByTenTK(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if passwords match (consider using BCryptPasswordEncoder for this)
        if (!taiKhoanService.passwordsMatch(password, taiKhoan.getPassword())) {
            return "redirect:/Admin/login?error";
        }
        Collection<? extends GrantedAuthority> authorities = taiKhoan.getAuthorities();
        for (GrantedAuthority authority : authorities) {
//            System.out.println("Authority: " + authority.getAuthority());


            if (Objects.equals(authority.getAuthority(), "ADMIN")) {
                model.addAttribute("username",authority.getAuthority());
                // If user has ROLE_ADMIN, redirect to admin home page
                return "redirect:/Admin/Home";
            } else {
                // If user does not have ROLE_ADMIN, redirect to login page with error
                return "redirect:/Admin/login?error";
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
