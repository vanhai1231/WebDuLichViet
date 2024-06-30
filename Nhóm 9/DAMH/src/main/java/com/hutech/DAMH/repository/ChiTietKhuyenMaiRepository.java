package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.ChiTietKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChiTietKhuyenMaiRepository extends JpaRepository<ChiTietKhuyenMai, String> {
    ChiTietKhuyenMai findByMaTour(String maTour);
}
