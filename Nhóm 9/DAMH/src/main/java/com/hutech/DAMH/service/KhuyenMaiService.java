package com.hutech.DAMH.service;

import com.hutech.DAMH.model.KhuyenMai;

import com.hutech.DAMH.repository.KhuyenMaiRespository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KhuyenMaiService {
    @Autowired
    private KhuyenMaiRespository khuyenMaiRespository;

    public void save(@NotNull KhuyenMai khuyenMai) {


        khuyenMaiRespository.save(khuyenMai);
    }
}
