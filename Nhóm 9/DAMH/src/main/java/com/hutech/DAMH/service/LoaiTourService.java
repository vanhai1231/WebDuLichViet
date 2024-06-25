package com.hutech.DAMH.service;

import com.hutech.DAMH.model.LoaiTour;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.repository.LoaiTourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiTourService {
    @Autowired
    private LoaiTourRepository loaiTourRepository;
    public List<LoaiTour> getAllLoaiTour() {
        return loaiTourRepository.findAll();
    }
}
