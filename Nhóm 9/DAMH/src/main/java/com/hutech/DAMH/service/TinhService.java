package com.hutech.DAMH.service;
import com.hutech.DAMH.model.Tinh;
import com.hutech.DAMH.repository.TinhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TinhService {
    @Autowired
    private TinhRepository TinhRepository;

    public List<Tinh> getSuggestions(String query) {
        return TinhRepository.findBytenTinhContainingIgnoreCase(query);
    }
}
