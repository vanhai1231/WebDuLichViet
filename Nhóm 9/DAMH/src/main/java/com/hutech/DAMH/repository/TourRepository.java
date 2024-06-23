package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.PhuongTien;
import com.hutech.DAMH.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {

    Tour findByMaTour(String maTour);
}