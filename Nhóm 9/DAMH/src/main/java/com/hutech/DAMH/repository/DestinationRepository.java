package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Destination;
import com.hutech.DAMH.model.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    List<Destination> findByMaTinh_MaTinh(String maTinh);

}
