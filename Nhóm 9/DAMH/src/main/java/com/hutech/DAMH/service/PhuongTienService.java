package com.hutech.DAMH.service;

import com.hutech.DAMH.model.PhuongTien;
import com.hutech.DAMH.repository.PhuongTienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhuongTienService {
    @Autowired
    private PhuongTienRepository phuongTienRepository;
    public List<PhuongTien> getAllPhuongTien(){return phuongTienRepository.findAll();}
}
