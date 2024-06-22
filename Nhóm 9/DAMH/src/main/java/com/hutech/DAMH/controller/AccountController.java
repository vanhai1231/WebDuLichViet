package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.User;

import com.hutech.DAMH.repository.TaiKhoanRepository;
import com.hutech.DAMH.repository.UserRepository;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.service.TaiKhoanService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller // Đánh dấu lớp này là một Controller trong Spring MVC.
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {
    private final TaiKhoanService taiKhoanService;


    @GetMapping("/DangNhap")
    public String login(Model model) {
        model.addAttribute("formType", "login");
        return "users/loginandregister";
    }

    @GetMapping("/DangKy")
    public String register(@NotNull Model model) {
        model.addAttribute("formType", "register");
        model.addAttribute("user", new TaiKhoan()); // Thêm một đối tượng User mới vào model
        return "users/loginandregister";
    }
    @PostMapping("/DangKy")
    public ResponseEntity<?> register(@Valid @ModelAttribute("user") TaiKhoan user,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(Map.of("errors", errors));
        }

        if (taiKhoanService.findByTenTK(user.getTenTK()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("errors", Collections.singletonList("Tên tài khoản đã tồn tại")));
        }
        if (taiKhoanService.findByEmail(user.getUser().getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("errors", Collections.singletonList("Email đã tồn tại")));
        }
        if (taiKhoanService.findBySdt(user.getUser().getSdt()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("errors", Collections.singletonList("Số điện thoại đã tồn tại")));
        }
        taiKhoanService.save(user);
        taiKhoanService.setDefaultRole(user.getTenTK());

        return ResponseEntity.ok().body(Map.of("message", "Đăng ký thành công"));
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




}
