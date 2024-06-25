package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//public interface TourRepository extends JpaRepository<Tour, String> {
//    Tour findByMaTour(String maTour);
//}
public interface TourRepository extends JpaRepository<Tour, String>, JpaSpecificationExecutor<Tour> {

    Tour findByMaTour(String maTour);
}