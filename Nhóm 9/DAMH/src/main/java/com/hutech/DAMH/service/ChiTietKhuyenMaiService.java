package com.hutech.DAMH.service;

import com.hutech.DAMH.model.ChiTietKhuyenMai;
import com.hutech.DAMH.repository.ChiTietKhuyenMaiRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ChiTietKhuyenMaiService {
    private final ChiTietKhuyenMaiRepository chiTietKhuyenMaiRepository;
    public ChiTietKhuyenMai findByMaTour(String maTour) {
        return chiTietKhuyenMaiRepository.findByMaTour(maTour);
    }
    public List<ChiTietKhuyenMai> findAll() {
        return chiTietKhuyenMaiRepository.findAll();
    }
    public void save(ChiTietKhuyenMai chiTietKhuyenMai) {
        chiTietKhuyenMaiRepository.save(chiTietKhuyenMai);
    }
    public List<ChiTietKhuyenMai> findByMaKM(String maKM) {
        return chiTietKhuyenMaiRepository.findByMaKM(maKM);
    }
}
