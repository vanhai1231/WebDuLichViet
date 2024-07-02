package com.hutech.DAMH.service;
import com.hutech.DAMH.CustomUserDetails;
import com.hutech.DAMH.Role;
import com.hutech.DAMH.model.*;

import com.hutech.DAMH.repository.IRoleRepository;
import com.hutech.DAMH.repository.ITaiKhoanRepository;
import com.hutech.DAMH.repository.IUserRepository;
import com.hutech.DAMH.repository.UserRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<TaiKhoan> getAllTaiKhoan (){return taiKhoanRepository.findAll();}
    public Optional<TaiKhoan> getTaiKhoanId(int ID) {
        return taiKhoanRepository.findById(String.valueOf(ID));
    }
    //số tài khoản
    public long countUserAccounts() {
        return taiKhoanRepository.findAll().stream()
                .filter(taiKhoan -> taiKhoan.getRoles().stream()
                        .anyMatch(role -> role.getName().equals("USER")))
                .count();
    }
    
    // Lưu người dùng mới vào cơ sở dữ liệu sau khi mã hóa mật khẩu.
    public void save(@NotNull TaiKhoan user) {
        user.setPassWord(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user.getUser());
        taiKhoanRepository.save(user);
    }
    //Them  Nhan Vien
    public TaiKhoan addAcocount(TaiKhoan taiKhoan) {
        User user = taiKhoan.getUser();
        if (user != null) {
            user = userRepository.save(user);
            taiKhoan.setUser(user);
        }
        taiKhoan.setPassWord(new BCryptPasswordEncoder().encode(taiKhoan.getPassword()));
        return taiKhoanRepository.save(taiKhoan);
    }

    public TaiKhoan updateTaiKhoan(TaiKhoan taiKhoan) {
        TaiKhoan existingTaiKhoan = taiKhoanRepository.findById(String.valueOf(taiKhoan.getID())).orElse(null);
        if (existingTaiKhoan != null) {
            existingTaiKhoan.setTenTK(taiKhoan.getTenTK());
            existingTaiKhoan.setPassWord(new BCryptPasswordEncoder().encode(taiKhoan.getPassword()));




            User user = taiKhoan.getUser();
            if (user != null) {
                existingTaiKhoan.getUser().setHoTen(user.getHoTen());
                existingTaiKhoan.getUser().setEmail(user.getEmail());
                existingTaiKhoan.getUser().setSdt(user.getSdt());
                existingTaiKhoan.getUser().setDiaChi(user.getDiaChi());
            }
            if (taiKhoan.getRoles() != null) {
                existingTaiKhoan.setRoles(taiKhoan.getRoles());
            }
            return taiKhoanRepository.save(existingTaiKhoan);
        }
        return null;
    }
    public void deleteTaiKhoan(int ID) {
        TaiKhoan taiKhoan = taiKhoanRepository.findById(String.valueOf(ID))
                .orElseThrow(() -> new IllegalStateException("Account with ID " + ID + " does not exist."));


        User user = taiKhoan.getUser();

        // Delete the associated User entity if it exists
        if (user != null) {
            userRepository.delete(user);
        }



        taiKhoanRepository.delete(taiKhoan);
    }
    // Gán vai trò mặc định cho người dùng dựa trên tên người dùng.
    public void setDefaultRoleNhanVien(String tenTK) {
        taiKhoanRepository.findByTenTK(tenTK).ifPresentOrElse(
                user -> {

                    user.getRoles().add(roleRepository.findRoleById(Role.EMPLOYEE.value));
                    taiKhoanRepository.save(user);
                },
                () -> { throw new UsernameNotFoundException("User not found"); }
        );
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
//    @Override
//    public UserDetails loadUserByUsername(String tenTK) throws UsernameNotFoundException {
//        var user = taiKhoanRepository.findByTenTK(tenTK)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(user.getTenTK())
//                .password(user.getPassword())
//                .authorities(user.getAuthorities())
//                .accountExpired(!user.isAccountNonExpired())
//                .accountLocked(!user.isAccountNonLocked())
//                .credentialsExpired(!user.isCredentialsNonExpired())
//                .disabled(!user.isEnabled())
//                .build();
//    }
    @Override
    public UserDetails loadUserByUsername(String tenTK) throws UsernameNotFoundException {
        TaiKhoan user = taiKhoanRepository.findByTenTK(tenTK)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(
                user.getTenTK(),
                user.getPassword(),
                user.getID(),
                user.getAuthorities()
        );
    }

    // Tìm kiếm người dùng dựa trên tên đăng nhập.
    public Optional<TaiKhoan> findByTenTK(String tenTK) throws
            UsernameNotFoundException {
        return taiKhoanRepository.findByTenTK(tenTK);
    }
//    public Optional<User> findByEmail(String email) throws
//            UsernameNotFoundException {
//        return iUserRepository.findByEmail(email);
//    }
public Optional<TaiKhoan> findByEmail(String email) throws UsernameNotFoundException {
    Optional<User> userOptional = iUserRepository.findByEmail(email);
    if (userOptional.isPresent()) {
        return taiKhoanRepository.findByUser(userOptional.get());
    }
    return Optional.empty();
}
    public Optional<User> findBySdt(int sdt) throws
            UsernameNotFoundException {
        return iUserRepository.findBySdt(sdt);
    }
    public boolean passwordsMatch(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }
    public boolean hasRole(String roleName) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(roleName)) {
                return true;
            }
        }
        return false;
    }


}
