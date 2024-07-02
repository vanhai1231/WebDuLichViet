package com.hutech.DAMH.service;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.repository.HoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Override
    @Transactional
    public void saveHoaDon(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

    @Override
    public List<HoaDon> findByTaiKhoan(TaiKhoan taiKhoan) {
        return List.of();
    }

    @Override
    public HoaDon findByMaHD(String maHD) {
        return null;
    }

    @Override
    public List<HoaDon> findById(String ID) {
        return List.of();
    }


    @Override
    public List<HoaDon> findById(int ID) {
        return hoaDonRepository.findByTaiKhoan_ID(ID);
    }


}
