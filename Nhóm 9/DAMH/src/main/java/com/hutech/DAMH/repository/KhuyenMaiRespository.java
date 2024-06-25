package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.KhuyenMai;
import com.hutech.DAMH.model.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KhuyenMaiRespository extends JpaRepository<KhuyenMai, String> {
    Optional<KhuyenMai> findByMaKM(String MaKM);
}
