package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
    Tour findByMaTour(String maTour);
}