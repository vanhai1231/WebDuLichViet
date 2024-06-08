package com.hutech.DAMH.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "TenTK", unique = true, nullable = false)
    private String tenTK;

    @Column(name = "PassWord", nullable = false)
    private String passWord;

    @ManyToOne
    @JoinColumn(name = "idUser", insertable = false, updatable = false)
    private User user;
}

