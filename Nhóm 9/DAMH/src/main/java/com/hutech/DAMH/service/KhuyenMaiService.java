package com.hutech.DAMH.service;

import com.hutech.DAMH.model.KhuyenMai;

import com.hutech.DAMH.repository.KhuyenMaiRespository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiService {
    @Autowired
    private KhuyenMaiRespository khuyenMaiRespository;

    public void save(@NotNull KhuyenMai khuyenMai) {

        khuyenMaiRespository.save(khuyenMai);
    }
    public List<KhuyenMai> findAll() {
        return khuyenMaiRespository.findAll();
    }
    public Optional<KhuyenMai> findByMaKM(String maKM) {
        return khuyenMaiRespository.findByMaKM(maKM);
    }

}
