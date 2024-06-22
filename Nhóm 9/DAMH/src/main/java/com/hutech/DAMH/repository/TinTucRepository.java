package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TinTuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TinTucRepository extends JpaRepository<TinTuc, Long> {
   // List<TinTuc> findByNameContaining(String keyword);
}