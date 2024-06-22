package com.hutech.DAMH.service;
import com.hutech.DAMH.model.ThanhPho;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.repository.ThanhPhoRepository;
import com.hutech.DAMH.repository.TinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ThanhPhoService {
    @Autowired
    private ThanhPhoRepository thanhPhoRepository;

    public List<ThanhPho> getSuggestions(String query) {
        return thanhPhoRepository.findBytenTPContainingIgnoreCase(query);
    }
}
