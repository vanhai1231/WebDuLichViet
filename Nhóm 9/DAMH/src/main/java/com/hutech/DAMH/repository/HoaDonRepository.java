package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    // Các phương thức query custom nếu cần
}
