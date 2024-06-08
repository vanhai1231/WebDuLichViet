package com.hutech.DAMH.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private String hoTen;

    @Column(name = "Email")
    private String email;

    @Column(name = "SDT")
    private int sdt;

    @Column(name = "DiaChi", columnDefinition = "nvarchar(250)")
    private String diaChi;

    // Constructors, getters and setters
}

