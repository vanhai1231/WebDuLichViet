package com.hutech.DAMH.service;

import com.hutech.DAMH.model.Phong;
import com.hutech.DAMH.repository.PhongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhongService {
    @Autowired
    private PhongRepository phongRepository;
    public List<Phong> getAllPhong(){
        return  phongRepository.findAll();
    }
}
