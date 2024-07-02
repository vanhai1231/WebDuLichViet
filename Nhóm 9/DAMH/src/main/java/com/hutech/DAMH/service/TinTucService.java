package com.hutech.DAMH.service;

import com.hutech.DAMH.model.TinTuc;
import com.hutech.DAMH.repository.TinTucRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TinTucService {

    private final TinTucRepository tinTucRepository;

    @Autowired
    public TinTucService(TinTucRepository tinTucRepository) {
        this.tinTucRepository = tinTucRepository;
    }

    public List<TinTuc> getAllBlogs() {
        return tinTucRepository.findAll();
    }

    public Optional<TinTuc> getBlogById(int id) {
        return tinTucRepository.findById((long) id);
    }

    public Optional<TinTuc> findById(int id) {

        return Optional.empty();
    }
}