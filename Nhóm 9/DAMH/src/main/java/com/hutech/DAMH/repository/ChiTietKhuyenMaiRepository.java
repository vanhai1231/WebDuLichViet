package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.ChiTietKhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChiTietKhuyenMaiRepository extends JpaRepository<ChiTietKhuyenMai, String> {
    ChiTietKhuyenMai findByMaTour(String maTour);
    @Query("SELECT c FROM ChiTietKhuyenMai c JOIN c.tour t JOIN c.khuyenMai km WHERE km.maKM = :maKM")
    List<ChiTietKhuyenMai> findByMaKM(@Param("maKM") String maKM);
}
