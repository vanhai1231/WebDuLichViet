package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface ITaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    Optional<TaiKhoan> findByTenTK(String tenTK);

    Optional<TaiKhoan> findByUser(User user);
}