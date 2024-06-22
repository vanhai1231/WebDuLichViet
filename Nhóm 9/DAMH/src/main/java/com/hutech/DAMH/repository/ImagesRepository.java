package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Integer> {

    List<Images> findByMaTour(String maTour);
}
