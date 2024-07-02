package com.hutech.DAMH.service;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.KhachSan;
import com.hutech.DAMH.repository.KhachSanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class KhachSanService {
    private final KhachSanRepository khachSanRepository;
    public List<KhachSan> getALLKhachSan(){return khachSanRepository.findAll();}
    public Optional<KhachSan> getKhachSanId(String maKS) {
        return khachSanRepository.findById(maKS);
    }
    public KhachSan addKhachSan(KhachSan khachsan) {
        return khachSanRepository.save(khachsan);
    }
    public KhachSan updateKhachSan(@NotNull KhachSan khachsan) {
        KhachSan existingKhachSan = khachSanRepository.findById(khachsan.getMaKS())
                .orElseThrow(() -> new IllegalStateException("KhachSan with ID " +
                        khachsan.getMaKS() + " does not exist."));


        existingKhachSan.setDiaChi(khachsan.getDiaChi());
        existingKhachSan.setDanhGia(khachsan.getDanhGia());
        existingKhachSan.setTenKS(khachsan.getTenKS());

        return khachSanRepository.save(existingKhachSan);
    }
    public void deleteKSById(String id) {
        if (!khachSanRepository.existsById(id)) {
            throw new IllegalStateException("Khach San with ID " + id + " does not exist.");
        }
        khachSanRepository.deleteById(id);
    }
    //đếm số khách sạn
    public long countAllKhachSan() {
        return khachSanRepository.count();
    }
}
