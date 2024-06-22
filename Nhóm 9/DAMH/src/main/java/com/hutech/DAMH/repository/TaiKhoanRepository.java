package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    TaiKhoan findByTenTK(String tenTK);
}
