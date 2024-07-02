package com.hutech.DAMH.service;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;

import java.util.List;
import java.util.Optional;

public interface HoaDonService {
    void saveHoaDon(HoaDon hoaDon);
    List<HoaDon> findByTaiKhoan(TaiKhoan taiKhoan);

    HoaDon findByMaHD(String maHD);
    List<HoaDon> findById(String ID);

    List<HoaDon> findById(int ID);
}
