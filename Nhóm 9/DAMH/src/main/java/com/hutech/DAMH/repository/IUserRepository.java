package com.hutech.DAMH.repository;

import com.hutech.DAMH.model.TaiKhoan;
import com.hutech.DAMH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
    Optional<User> findBySdt(Integer sdt);
}
