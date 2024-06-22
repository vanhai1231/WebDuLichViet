package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Tinh;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TinhRepository extends JpaRepository<Tinh, String> {
    List<Tinh> findBytenTinhContainingIgnoreCase(String tenTinh);
}
