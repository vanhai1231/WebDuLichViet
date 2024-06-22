package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.ThanhPho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThanhPhoRepository extends JpaRepository<ThanhPho, String> {
    List<ThanhPho> findBytenTPContainingIgnoreCase(String tenTP);
}
