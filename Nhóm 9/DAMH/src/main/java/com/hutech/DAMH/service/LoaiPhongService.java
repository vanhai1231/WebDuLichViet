package com.hutech.DAMH.service;

import com.hutech.DAMH.model.LoaiPhong;
import com.hutech.DAMH.model.LoaiTour;
import com.hutech.DAMH.repository.LoaiPhongRepository;
import com.hutech.DAMH.repository.LoaiTourRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiPhongService {
    @Autowired
    private LoaiPhongRepository loaiPhongRepository;
    public List<LoaiPhong> getAllLoaiPhong() {
        return loaiPhongRepository.findAll();
    }
    public Optional<LoaiPhong> getMaLoaiPhong(String maLoai) {
        return loaiPhongRepository.findById(maLoai);
    }
    public LoaiPhong addLP(LoaiPhong maLoai) {
        return loaiPhongRepository.save(maLoai);
    }
    public LoaiPhong updateLP(@NotNull LoaiPhong loaiPhong) {
        LoaiPhong existingLoaiTour = loaiPhongRepository.findById(loaiPhong.getMaLoai())
                .orElseThrow(() -> new IllegalStateException("loaiPhong with ID " +
                        loaiPhong.getMaLoai() + " does not exist."));


        existingLoaiTour.setTenLoai(loaiPhong.getTenLoai());


        return loaiPhongRepository.save(existingLoaiTour);
    }
    public void deleteLPById(String id) {
        if (!loaiPhongRepository.existsById(id)) {
            throw new IllegalStateException("loaiPhong with ID " + id + " does not exist.");
        }
        loaiPhongRepository.deleteById(id);
    }
}
