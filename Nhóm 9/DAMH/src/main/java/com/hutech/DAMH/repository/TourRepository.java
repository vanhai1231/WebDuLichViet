package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//public interface TourRepository extends JpaRepository<Tour, String> {
//    Tour findByMaTour(String maTour);
//}
public interface TourRepository extends JpaRepository<Tour, String>, JpaSpecificationExecutor<Tour> {
    @Query("SELECT t FROM Tour t WHERE t.soluong > 0")
    List<Tour> findAllWithQuantityGreaterThanZero();
    Tour findByMaTour(String maTour);
    @Query("SELECT t FROM Tour t WHERE t.soluong > 0")
    Page<Tour> findAllWithQuantityGreaterThanZero(Pageable pageable);
}