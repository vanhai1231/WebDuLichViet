package com.hutech.DAMH.controller;

import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.Role;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.repository.IRoleRepository;
import com.hutech.DAMH.service.OTPService;
import com.hutech.DAMH.service.RoleService;
import com.hutech.DAMH.service.TaiKhoanService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Controller
@AllArgsConstructor
@SessionAttributes("username")
@RequestMapping("/Admin") // các yêu cầu đến đường dẫn bắt đầu bằng /Admin đều được định tuyeens đến AccountAdminController
public class AccountAdminController {
    private final TaiKhoanService taiKhoanService;
    private final RoleService roleService;
    private final OTPService otpService;

    @GetMapping("DanhSachTaiKhoan")
    public String ShowAccount(Model model, HttpServletRequest request){
        HttpSession session = request.getSession(false); // Check existing session

        if (session == null || session.getAttribute("username") == null) {
            return "redirect:/Admin/login";
        }

        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        boolean isAdmin = "ADMIN".equals(username);
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("taikhoans",taiKhoanService.getAllTaiKhoan());
        return "/Admin/account-list";
    }
    @GetMapping("/ThemTaiKhoan")
    public String showAddFormTK(Model model) {
        model.addAttribute("taikhoan", new TaiKhoan());

        return "/Admin/add-account";
    }

    @PostMapping("/ThemTK")
    public String addTaiKhoan(@ModelAttribute("taikhoan") TaiKhoan taikhoan) {
        try {
            taiKhoanService.addAcocount(taikhoan);
            taiKhoanService.setDefaultRoleNhanVien(taikhoan.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ThemTaiKhoan?error";
        }
        return "redirect:/Admin/DanhSachTaiKhoan";


    }

    @GetMapping("/SuaTaiKhoan/{id}")
    public String showUpdateForm(@PathVariable("id") int ID, Model model) {
        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanId(ID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tai khoan Id:" + ID));
        model.addAttribute("taikhoan", taiKhoan);
        model.addAttribute("roles", roleService.getAllRoles());

        return "/Admin/update-account";
    }
    // Process the form for updating a product
    @PostMapping("/CapNhatTaiKhoan/{id}")
    public String updateTaiKhoan(@PathVariable("id") int ID, @ModelAttribute("taikhoan") TaiKhoan taikhoan) {


        try {

            taikhoan.setID(ID);

            taiKhoanService.updateTaiKhoan(taikhoan);

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/SuaTaiKhoan/" + taikhoan.getID() + "?error";
        }
        return "redirect:/Admin/DanhSachTaiKhoan";
    }
    @GetMapping("/XoaTaiKhoan/{id}")
    public String deleteTour(@PathVariable("id") int ID) {
        taiKhoanService.deleteTaiKhoan(ID);
        return "redirect:/Admin/DanhSachTaiKhoan";
    }
    @GetMapping("/ResetPassword/{id}")
    public String showResetPasswordForm(@PathVariable("id") int ID, Model model) {
        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanId(ID)
                .orElseThrow(() -> new IllegalArgumentException("Invalid tai khoan Id:" + ID));
        model.addAttribute("taikhoan", taiKhoan);
        return "/Admin/reset-password";
    }
    @PostMapping("/ResetPassword/{id}")
    public String resetPassword(@PathVariable("id") int ID,@RequestParam("email")String email,Model model) {
        try {
            taiKhoanService.getTaiKhoanId(ID)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid tai khoan Id:" + ID));
            TaiKhoan taiKhoan;

            // You should hash the password before saving it
            Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByEmail(email);
            if (optionalTaiKhoan.isPresent()) {
                taiKhoan = optionalTaiKhoan.get();
                String newPassword = otpService.generateRandomPassword();
                taiKhoan.setPassWord(newPassword);
                taiKhoanService.save(taiKhoan);

                String resetLink = "http://localhost:8080/Admin/ChangePassword?email=" + email; // Update with your domain and path
                otpService.sendNewPasswordEmail(taiKhoan.getUser().getEmail(), newPassword, resetLink);
                model.addAttribute("message", "A new password has been sent to your email.");
            } else {
                model.addAttribute("error", "Email not found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/Admin/ResetPassword/" + ID + "?error";
        }
        return "redirect:/Admin/DanhSachTaiKhoan";
    }
    @GetMapping("/ChangePassword")
    public String resetPasswordForm(@RequestParam("email") String email, Model model) {
        model.addAttribute("email", email);
        return "/Admin/change-password";
    }

    @PostMapping("/ChangePassword")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        Optional<TaiKhoan> optionalTaiKhoan = taiKhoanService.findByEmail(email);
        if (optionalTaiKhoan.isPresent()) {
            TaiKhoan taiKhoan = optionalTaiKhoan.get();
            taiKhoan.setPassWord(password); // Encode new password
            taiKhoanService.save(taiKhoan);
            model.addAttribute("message", "Password has been reset successfully.");
            return "redirect:/Admin/DanhSachTaiKhoan"; // Redirect to login page after reset
        } else {
            model.addAttribute("error", "Email not found.");
            return "/Admin/ChangePassword"; // Return to reset password form with error message
        }
    }
}
