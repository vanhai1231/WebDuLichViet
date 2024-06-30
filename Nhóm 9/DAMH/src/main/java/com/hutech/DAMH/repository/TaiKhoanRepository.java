package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.service.TaiKhoanService;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    TaiKhoan findByTenTK(String tenTK);
    TaiKhoan findByID(int ID);
}
