package com.hutech.DAMH.model;


import jakarta.persistence.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;
import java.util.List;

@Entity
@IdClass(ImagesId.class)
@Table(name = "Images")
public class Images {

    @Id
    @Column(name = "MaTour", nullable = false, length = 10)
    private String maTour;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "MaTour", insertable = false, updatable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "ID", insertable = false, updatable = false)
    private HinhAnh hinhAnh;

    // Constructor
    public Images() {}

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTour() {
        return maTour;
    }

    public void setMaTour(String maTour) {
        this.maTour = maTour;
    }


}

