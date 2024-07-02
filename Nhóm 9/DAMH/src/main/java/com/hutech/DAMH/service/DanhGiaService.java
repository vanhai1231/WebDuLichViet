package com.hutech.DAMH.service;

import com.hutech.DAMH.model.DanhGia;
import com.hutech.DAMH.repository.DanhGiaRepository;
import com.hutech.DAMH.repository.KhachSanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DanhGiaService {
    private final DanhGiaRepository danhGiaRepository;
    public List<DanhGia> getAllDanhGia(){return danhGiaRepository.findAll();}
}
