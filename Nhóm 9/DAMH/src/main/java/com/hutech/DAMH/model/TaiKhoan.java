package com.hutech.DAMH.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements UserDetails {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "TenTK", unique = true, nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String tenTK;

    @Column(name = "PassWord", nullable = false)
    @NotBlank(message = "Password is required")
    private String passWord;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    // Getters and Setters




    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "taikhoan_role",
            joinColumns = @JoinColumn(name = "taikhoan_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRoles = this.getRoles();
        return userRoles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList();
    }
    @Override
    public String getPassword() {
        return passWord;
    }


    @Override
    public String getUsername() {
        return tenTK;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return
                false;
        TaiKhoan user = (TaiKhoan) o;
        return getID() != 0 && Objects.equals(getID(), user.getID());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }



}