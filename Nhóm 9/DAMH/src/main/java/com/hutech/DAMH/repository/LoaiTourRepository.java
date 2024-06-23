package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.LoaiTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiTourRepository extends JpaRepository<LoaiTour, String> {
}
