package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {

    List<HoaDon> findByTaiKhoan(TaiKhoan taiKhoan);

    HoaDon findByMaHD(String maHD);
    List<HoaDon> findByTaiKhoan_ID(Integer ID);

}
