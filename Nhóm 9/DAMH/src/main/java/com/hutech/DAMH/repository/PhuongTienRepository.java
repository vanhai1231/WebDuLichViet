package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.PhuongTien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhuongTienRepository extends JpaRepository<PhuongTien, String> {
    PhuongTien findByMaPhuongTien(String maPhuongTien);
}