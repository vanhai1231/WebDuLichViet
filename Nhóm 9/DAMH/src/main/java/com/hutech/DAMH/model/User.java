package com.hutech.DAMH.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "HoTen")
//    @NotBlank(message = "Vui lòng điền họ tên")
//    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String hoTen;

    @Column(name = "Email", unique = true)
    @NotBlank(message = "Vui lòng điền email")
    @Size(min = 1, max = 50, message = "Email phải từ 1 -> 50 ký tự")
    @Email(message = "Địa chỉ email không hợp lệ")
    private String email;

    @Column(name = "SDT", length = 10,unique = true)
//    @NotNull(message = "Số điện thoại không được để trống")
//    @Digits(integer = 10, fraction = 0, message = "Số điện thoại phải là số có 10 chữ số")
    private int sdt;

    @Column(name = "DiaChi", columnDefinition = "nvarchar(250)")
//    @NotEmpty(message = "Số điện thoại không được để trống")
    private String diaChi;

    // Constructors, getters and setters
}