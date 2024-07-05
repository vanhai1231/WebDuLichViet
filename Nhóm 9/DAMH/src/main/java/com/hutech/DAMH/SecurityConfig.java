package com.hutech.DAMH;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.service.TaiKhoanService;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration // Đánh dấu lớp này là một lớp cấu hình cho Spring Context.
@EnableWebSecurity // Kích hoạt tính năng bảo mật web của Spring Security.
@RequiredArgsConstructor // Lombok tự động tạo constructor có tham số cho tất cả các trường final.
public class SecurityConfig {
    private final TaiKhoanService taiKhoanServiceService; // Tiêm UserService vào lớp cấu hình này.
    @Bean // Đánh dấu phương thức trả về một bean được quản lý bởi Spring Context.
    public UserDetailsService userDetailsService() {
        return new TaiKhoanService(); // Cung cấp dịch vụ xử lý chi tiết người dùng.
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean mã hóa mật khẩu sử dụng BCrypt.
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var auth = new DaoAuthenticationProvider(); // Tạo nhà cung cấp xác thực.
        auth.setUserDetailsService(userDetailsService()); // Thiết lập dịch vụ chi tiết người dùng.
        auth.setPasswordEncoder(passwordEncoder()); // Thiết lập cơ chế mã hóa mật khẩu.
        return auth; // Trả về nhà cung cấp xác thực.
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/assets/**","/CSS/**","webjars/**", "/js/**","/images/**", "/video/**",
                                "/", "/oauth/**", "/index/**", "/error","/Admin/SuaTaiKhoan/**","/Admin/ResetPassword/**","/Admin/DanhSachLoaiTour/**",
                                "/Admin/CapNhatTaiKhoan/**","/Admin/XoaTaiKhoan/**","/Admin/ChangePassword",
                                "/Admin/ThemLoaiTour","/Admin/ThemLT","/Admin/SuaLoaiTour/**","/Admin/UpdateLoaiTour/**","/Admin/XoaLoaiTour/**",
                                "/Admin/ThemTaiKhoan","/Admin/ThemTK","/Admin/ThemKhachSan","/Admin/ThemKS","/Admin/SuaKhachSan/**",
                                "/Admin/editKhachSan/**", "/Admin/SuaPhong/**", "/Admin/CapNhatPhong/**","/Admin/ThemPhong","/Admin/XoaPhong/**",
                                "/Admin/DanhSachPhong","/Admin/DanhSachTaiKhoan","/Admin/DanhSachKhachSan","/Admin/SuaHoaDon/**",
                                "/Admin/editHoaDon/**","/Admin/DanhSachHoaDon","/Admin/delete/**","/Admin/XoaKhachSan/**",
                                "/Admin/UpdateTour/**","/Admin/edit/**","/Admin/ThemTour","/Admin/SuaLoaiPhong/**","/Admin/CapNhatLoaiPhong/**",
                                "/Admin/ThemTour?error","/Admin/AddTour","/Admin/logoutAdmin","/Admin/XoaLoaiPhong/**","/Admin/ThemLoaiPhong/**",
                                "/Admin/LoiXoaDiemDen", "/Admin/XoaDiemDen/**", "/Admin/DanhSachTour","/Admin/login?error","/Admin/login","/DangNhap",
                                "/Admin/DanhSachDiemDen","/Admin/SuaDiemDen/**","/Admin/CapNhatDiemDen/**",
                                "/Admin/Home","/DuLichViet/Home","/NhapOtp","/verifyOtp","/DoiMatKhau","/Admin/DanhSachLoaiPhong",
                                "/QuenMatKhau","/DuLichViet/About","/DuLichViet/Blog","/DangKy","/LoiXoaPhong","/LoiXoaTaiKhoan","/LoiXoaKhachSan","/LoiXoaLoaiTour",
                                "/partials/**", "/cart/**", "/Admin/KhuyenMai", "/Admin/DanhSachKhuyenMai", "/DuLichViet/Tour",
                                "/DuLichViet/Destination", "/DuLichViet/Blog", "/DuLichViet/Contract", "/DuLichViet/Search",
                                "/Admin/ThemDiemDen", "/DuLichViet/FilterDestination")
                        .permitAll() // Cho phép truy cập không cần xác thực.
                        .requestMatchers("/products/edit/**", "/products/add", "/products/delete")
                        .hasAnyAuthority("ADMIN") // Chỉ cho phép ADMIN truy cập.
                        .requestMatchers("/api/**")
                        .permitAll() // API mở cho mọi người dùng.
                        .anyRequest().authenticated() // Bất kỳ yêu cầu nào khác cần xác thực.
                )
                .cors(withDefaults()) // Kích hoạt CORS với cấu hình mặc định từ CorsConfigurationSource
                .csrf(csrf -> csrf.disable()) // Vô hiệu hóa CSRF theo cách mới
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/DangNhap")
                        .defaultSuccessUrl("/google", true)
                        .failureUrl("/DangNhap?error")
                        .permitAll()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/DangNhap")
                        .defaultSuccessUrl("/facebook", true)
                        .failureUrl("/DangNhap?error")
                        .permitAll()
                )
//                .logout(logout -> logout
//                        .logoutUrl("/logoutAdmin")
//                        .logoutSuccessUrl("/Admin/login") // Trang chuyển hướng sau khi đăng xuất.
//                        .deleteCookies("JSESSIONID") // Xóa cookie.
//                        .invalidateHttpSession(true) // Hủy phiên làm việc.
//                        .clearAuthentication(true) // Xóa xác thực.
//                        .permitAll()
//                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/DangNhap") // Trang chuyển hướng sau khi đăng xuất.
                        .deleteCookies("JSESSIONID") // Xóa cookie.
                        .invalidateHttpSession(true) // Hủy phiên làm việc.
                        .clearAuthentication(true) // Xóa xác thực.
                        .permitAll()
                )
                //admin

//                .formLogin(formLogin1 -> formLogin1
//                        .loginPage("/Admin/login") // Trang đăng nhập.
//                        .loginProcessingUrl("/Admin/login") // URL xử lý đăng nhập.
//                        .defaultSuccessUrl("/Admin/Home",true) // Trang sau đăng nhập thành công.
//                        .failureUrl("/Admin/login?error") // Trang đăng nhập thất bại.
//                        .permitAll()
//
//                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/DangNhap") // Trang đăng nhập.
                        .loginProcessingUrl("/DangNhap") // URL xử lý đăng nhập.
                        .defaultSuccessUrl("/DuLichViet/Home",true) // Trang sau đăng nhập thành công.
                        .failureUrl("/DangNhap?error") // Trang đăng nhập thất bại.
                        .permitAll()

                )
                //admin


                .rememberMe(rememberMe -> rememberMe
                        .key("hutech")
                        .rememberMeCookieName("hutech")
                        .tokenValiditySeconds(24 * 60 * 60) // Thời gian nhớ đăng nhập.
                        .userDetailsService(userDetailsService())
                )
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedPage("/403") // Trang báo lỗi khi truy cập không được phép.
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1) // Giới hạn số phiên đăng nhập.
                        .expiredUrl("/DangNhap") // Trang khi phiên hết hạn.
                )

                .httpBasic(httpBasic -> httpBasic
                        .realmName("hutech") // Tên miền cho xác thực cơ bản.
                )
                .build(); // Xây dựng và trả về chuỗi lọc bảo mật.
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("*")); // Cho phép tất cả nguồn, bạn có thể chỉ định nguồn cụ thể tại đây.
        configuration.setAllowedHeaders(List.of("*")); // Cho phép tất cả headers, bạn có thể chỉ định headers cụ thể tại đây.
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration); // Cấu hình CORS cho đường dẫn API
        return source;
    }

}