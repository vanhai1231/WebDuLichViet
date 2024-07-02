package com.hutech.DAMH.service;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.repository.HoaDonRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import com.hutech.DAMH.model.TaiKhoan;

import java.util.List;
import java.util.Optional;
@Service
public class HoaDonServiceImpl implements HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;
    public List<HoaDon> getAllBills() {
        return hoaDonRepository.findAll();
    }
    public Optional<HoaDon> getHoaDonId(String maHD) {
        return hoaDonRepository.findById(maHD);
    }
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
    //lấy tổng tiền
    public BigDecimal getTotalTongTien() {
        List<HoaDon> allBills = hoaDonRepository.findAll();
        BigDecimal totalTongTien = BigDecimal.ZERO;
        for (HoaDon hoaDon : allBills) {
            totalTongTien = totalTongTien.add(hoaDon.getTongTien());
        }
        return totalTongTien;
    }

    //doanh thu theo tháng
    @Override
    public Map<String, BigDecimal> getRevenueByMonth() {
        List<HoaDon> allBills = hoaDonRepository.findAll();
        Map<String, BigDecimal> revenueByMonth = new LinkedHashMap<>();

        for (HoaDon hoaDon : allBills) {
            String monthYear = getMonthYear(hoaDon.getNgayLap());
            BigDecimal currentRevenue = revenueByMonth.getOrDefault(monthYear, BigDecimal.ZERO);
            BigDecimal newRevenue = currentRevenue.add(hoaDon.getTongTien());
            revenueByMonth.put(monthYear, newRevenue);
        }

        return revenueByMonth;
    }

    private String getMonthYear(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue() + "-" + localDate.getYear();
    }
    //đếm số hóa đơn
    public long countAllHoaDon() {
        return hoaDonRepository.count();
    }
    public HoaDon updateHoaDon(@NotNull HoaDon hoaDon) {
        HoaDon existingHoaDon = hoaDonRepository.findById(hoaDon.getMaHD())
                .orElseThrow(() -> new IllegalStateException("HoaDon with ID " +
                        hoaDon.getMaHD() + " does not exist."));


        existingHoaDon.setDiaChi(hoaDon.getDiaChi());
        existingHoaDon.setSdt(hoaDon.getSdt());
        existingHoaDon.setNgayLap(hoaDon.getNgayLap());
        existingHoaDon.setTongTien(hoaDon.getTongTien());
        existingHoaDon.setSoNguoiLon(hoaDon.getSoNguoiLon());
        existingHoaDon.setSoTreEm(hoaDon.getSoTreEm());
        existingHoaDon.setTrangThai(hoaDon.getTrangThai());
        return hoaDonRepository.save(existingHoaDon);
    }
}
