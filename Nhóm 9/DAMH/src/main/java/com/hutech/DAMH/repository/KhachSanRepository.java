package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.HoaDon;
import com.hutech.DAMH.model.KhachSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KhachSanRepository extends JpaRepository<KhachSan, String> {
}
