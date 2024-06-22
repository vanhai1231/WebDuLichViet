package com.hutech.DAMH.service;
import com.hutech.DAMH.Role;
import com.hutech.DAMH.model.TaiKhoan;

import com.hutech.DAMH.model.User;
import com.hutech.DAMH.repository.IRoleRepository;
import com.hutech.DAMH.repository.ITaiKhoanRepository;
import com.hutech.DAMH.repository.IUserRepository;
import com.hutech.DAMH.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Service
@Slf4j
@Transactional
public class TaiKhoanService implements UserDetailsService {
    @Autowired
    private ITaiKhoanRepository taiKhoanRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository roleRepository;
    // Lưu người dùng mới vào cơ sở dữ liệu sau khi mã hóa mật khẩu.
    public void save(@NotNull TaiKhoan user) {
        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user.getUser());
        taiKhoanRepository.save(user);
    }
    public TaiKhoan processOAuthPostLogin(OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String address = oauth2User.getAttribute("address");
        Optional<User> existUserssss = iUserRepository.findByEmail(email);

        TaiKhoan taiKhoan =null;
        User user=null;
        if (existUserssss.isEmpty()) {
            taiKhoan = new TaiKhoan();
            taiKhoan.setTenTK(email); // Assuming username is the email
//            taiKhoan.setPassWord("123"); // No password for OAuth users
            taiKhoan.setPassWord(new BCryptPasswordEncoder().encode("123"));
            user = new User();
            user.setEmail(email); // Set email for the new User
            user.setSdt(0); // Set email for the new User
            user.setHoTen(name); // Set email for the new User
            user.setDiaChi(address); // Set email for the new User
            iUserRepository.save(user);
            taiKhoan.setUser(user);
            taiKhoanRepository.save(taiKhoan);


            setDefaultRole(taiKhoan.getUsername()); // Pass TaiKhoan instead of User
        }

        return taiKhoan;
    }
    // Gán vai trò mặc định cho người dùng dựa trên tên người dùng.
    public void setDefaultRole(String tenTK) {
        taiKhoanRepository.findByTenTK(tenTK).ifPresentOrElse(
                user -> {

                    user.getRoles().add(roleRepository.findRoleById(Role.USER.value));
                    taiKhoanRepository.save(user);
                },
                () -> { throw new UsernameNotFoundException("User not found"); }
        );
    }
    // Tải thông tin chi tiết người dùng để xác thực.
    @Override
    public UserDetails loadUserByUsername(String tenTK) throws
            UsernameNotFoundException {
        var user = taiKhoanRepository.findByTenTK(tenTK)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getTenTK())
                .password(user.getPassword())
                .authorities(user.getAuthorities())
                .accountExpired(!user.isAccountNonExpired())
                .accountLocked(!user.isAccountNonLocked())
                .credentialsExpired(!user.isCredentialsNonExpired())
                .disabled(!user.isEnabled())
                .build();
    }
    // Tìm kiếm người dùng dựa trên tên đăng nhập.
    public Optional<TaiKhoan> findByTenTK(String tenTK) throws
            UsernameNotFoundException {
        return taiKhoanRepository.findByTenTK(tenTK);
    }
    public Optional<User> findByEmail(String email) throws
            UsernameNotFoundException {
        return iUserRepository.findByEmail(email);
    }
    public Optional<User> findBySdt(int sdt) throws
            UsernameNotFoundException {
        return iUserRepository.findBySdt(sdt);
    }


}
