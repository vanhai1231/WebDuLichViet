package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.KhuyenMai;
import com.hutech.DAMH.model.User;

import com.hutech.DAMH.repository.TaiKhoanRepository;
import com.hutech.DAMH.repository.UserRepository;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.service.KhuyenMaiService;
import com.hutech.DAMH.service.OTPService;
import com.hutech.DAMH.service.TaiKhoanService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final TaiKhoanService taiKhoanService;
    private final OTPService otpService;
    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @GetMapping("/DangNhap")
    public String login(Model model, Authentication authentication, HttpSession session) {
        if (isAuthenticated()) {

            return "redirect:/DuLichViet/Home";
        }

        model.addAttribute("formType", "login");
        return "users/loginandregister";
    }



    @GetMapping("/DangKy")
    public String register(@NotNull Model model) {
        if (isAuthenticated()) {

            return "redirect:/DuLichViet/Home";
        }
        model.addAttribute("formType", "register");
        model.addAttribute("user", new TaiKhoan()); // Thêm một đối tượng User mới vào model
        return "users/loginandregister";
    }
    @PostMapping("/DangKy")
    public ResponseEntity<?> register(@Valid @ModelAttribute("user") TaiKhoan user,
                                      BindingResult bindingResult,Model model,HttpSession session) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        List<Map.Entry<Boolean, String>> checks = List.of(
                Map.entry(taiKhoanService.findByTenTK(user.getTenTK()).isPresent(), "Tên tài khoản đã tồn tại"),
                Map.entry(taiKhoanService.findByEmail(user.getUser().getEmail()).isPresent(), "Email đã tồn tại"),
                Map.entry(taiKhoanService.findBySdt(user.getUser().getSdt()).isPresent(), "Số điện thoại đã tồn tại")
        );

        for (Map.Entry<Boolean, String> check : checks) {
            if (check.getKey()) {
                return ResponseEntity.badRequest().body(Map.of("errors", Collections.singletonList(check.getValue())));
            }
        }
        // Generate OTP and send email
        String otp = otpService.generateOTP();
        otpService.sendOTPEmail(user.getUser().getEmail(), otp);

        // Save the OTP and the user details in the session
        session.setAttribute("otp", otp);
        session.setAttribute("user", user);

        return ResponseEntity.ok().body(Map.of("message", "Đăng ký thành công. Vui lòng kiểm tra email để lấy mã OTP."));
    }
    @GetMapping("/NhapOtp")
    public String enterOtp() {
        return "otp_verification";
    }

    @PostMapping("/verifyOtp")
    public String verifyOtp(@RequestParam("otp") String inputOtp, HttpSession session, Model model) {
        String sessionOtp = (String) session.getAttribute("otp");
        TaiKhoan user = (TaiKhoan) session.getAttribute("user");

        if (sessionOtp != null && sessionOtp.equals(inputOtp)) {
            taiKhoanService.save(user);
            taiKhoanService.setDefaultRole(user.getUsername());
            generateAndSavePromoCode(user);
            session.removeAttribute("otp");
            session.removeAttribute("user");
            model.addAttribute("message", "Xác thực thành công. Tài khoản của bạn đã được tạo.");
            return "redirect:/DangNhap";
        } else {
            model.addAttribute("error", "Mã OTP không chính xác. Vui lòng thử lại.");
            return "otp_verification";
        }
    }
    @GetMapping("/QuenMatKhau")
    public String forgot() {
        return "users/forgot_password";
    }

    @PostMapping("/QuenMatKhau")
    public String forgotPassword(@RequestParam("email") String email, Model model){
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByEmail(email);
        if (optionalTaiKhoan.isPresent()) {
            TaiKhoan taiKhoan = optionalTaiKhoan.get();
            String newPassword = otpService.generateRandomPassword();
            taiKhoan.setPassWord(newPassword);
            taiKhoanService.save(taiKhoan);

            String resetLink = "http://localhost:8080/DoiMatKhau?email=" + email; // Update with your domain and path
            otpService.sendNewPasswordEmail(taiKhoan.getUser().getEmail(), newPassword, resetLink);
            model.addAttribute("message", "A new password has been sent to your email.");
        } else {
            model.addAttribute("error", "Email not found.");
        }
        return "users/forgot_password";
    }
    @GetMapping("/DoiMatKhau")
    public String resetPasswordForm(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "users/reset_password";
    }

    @PostMapping("/DoiMatKhau")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByEmail(email);
        if (optionalTaiKhoan.isPresent()) {
            TaiKhoan taiKhoan = optionalTaiKhoan.get();
            taiKhoan.setPassWord(password); // Encode new password
            taiKhoanService.save(taiKhoan);
            model.addAttribute("message", "Password has been reset successfully.");
            return "redirect:/login"; // Redirect to login page after reset
        } else {
            model.addAttribute("error", "Email not found.");
            return "users/reset_password"; // Return to reset password form with error message
        }
    }




    @GetMapping("/google")
    public String oauth2LoginSuccessGoogle(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        TaiKhoan taiKhoan = taiKhoanService.processOAuthPostLogin(oauth2User);
        model.addAttribute("user", taiKhoan);

        return "redirect:/DuLichViet/Home";
    }
    @GetMapping("/facebook")
    public String oauth2LoginSuccessFacebook(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        TaiKhoan taiKhoan = taiKhoanService.processOAuthPostLogin(oauth2User);
        model.addAttribute("user", taiKhoan);

        return "redirect:/DuLichViet/Home";
    }
    private void generateAndSavePromoCode(TaiKhoan user) {
        String promoCode = generatePromoCode(); // Method to generate a promo code
        // Save the promo code to the database associated with the user
        KhuyenMai promotion = new KhuyenMai();
        promotion.setMaKM(promoCode);
        promotion.setPhanTramKM(15);
        // Ngày bắt đầu là ngày hiện tại
        LocalDate startDate = LocalDate.now();
        // Ngày bắt đầu là ngày hiện tại
        java.util.Date currentDate = new java.util.Date();
        promotion.setNgayBatDau(new Date(currentDate.getTime()));

        // Ngày kết thúc là 15 ngày sau ngày bắt đầu
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 15);
        java.util.Date endDate = calendar.getTime();
        promotion.setNgayKetThuc(new Date(endDate.getTime()));
        promotion.setTaiKhoan(user);

        promotion.setSoLan(true);
        khuyenMaiService.save(promotion);
    }
    private String generatePromoCode() {
        // Simple example of promo code generation
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication.getPrincipal() instanceof String && "anonymousUser".equals(authentication.getPrincipal()));
    }


}
