package com.hutech.DAMH.service;

import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.LoaiTour;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.repository.LoaiTourRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiTourService {
    @Autowired
    private LoaiTourRepository loaiTourRepository;
    public List<LoaiTour> getAllLoaiTour() {
        return loaiTourRepository.findAll();
    }
    public Optional<LoaiTour> getMaLoaiTour(String maLoaiTour) {
        return loaiTourRepository.findById(maLoaiTour);
    }
    public LoaiTour addLT(LoaiTour loaiTour) {
        return loaiTourRepository.save(loaiTour);
    }
    public LoaiTour updateLT(@NotNull LoaiTour loaiTour) {
        LoaiTour existingLoaiTour = loaiTourRepository.findById(loaiTour.getMaLoaiTour())
                .orElseThrow(() -> new IllegalStateException("LoaiTour with ID " +
                        loaiTour.getMaLoaiTour() + " does not exist."));


        existingLoaiTour.setLoaiTour(loaiTour.getLoaiTour());


        return loaiTourRepository.save(existingLoaiTour);
    }
    public void deleteLTById(String id) {
        if (!loaiTourRepository.existsById(id)) {
            throw new IllegalStateException("LoaiTour with ID " + id + " does not exist.");
        }
        loaiTourRepository.deleteById(id);
    }
}
