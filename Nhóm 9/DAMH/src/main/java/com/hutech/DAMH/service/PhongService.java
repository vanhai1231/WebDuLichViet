package com.hutech.DAMH.service;

import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.model.Phong;
import com.hutech.DAMH.repository.PhongRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhongService {
    @Autowired
    private PhongRepository phongRepository;
    public List<Phong> getAllPhong(){
        return  phongRepository.findAll();
    }
    public Optional<Phong> getPhongId(String maPhong) {
        return phongRepository.findById(maPhong);
    }
    public Phong addPhong(Phong phong) {
        return phongRepository.save(phong);
    }
    public Phong updatePhong(@NotNull Phong phong) {
        Phong existingPhong = phongRepository.findById(phong.getMaPhong())
                .orElseThrow(() -> new IllegalStateException("Phong with ID " +
                        phong.getMaPhong() + " does not exist."));


        existingPhong.setGia(phong.getGia());
        existingPhong.setMoTa(phong.getMoTa());
        existingPhong.setKhachSan(phong.getKhachSan());
        existingPhong.setLoaiPhong(phong.getLoaiPhong());

        return phongRepository.save(existingPhong);
    }
    public void deletePById(String id) {
        if (!phongRepository.existsById(id)) {
            throw new IllegalStateException("Phong with ID " + id + " does not exist.");
        }
        phongRepository.deleteById(id);
    }
}
