package com.hutech.DAMH.service;

import com.hutech.DAMH.model.Tour;
import com.hutech.DAMH.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TourService {

    @Autowired
    private TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Tour getTourByMaTour(String maTour) {
        return tourRepository.findByMaTour(maTour);
    }
}