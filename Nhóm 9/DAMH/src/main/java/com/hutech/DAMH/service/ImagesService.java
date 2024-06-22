package com.hutech.DAMH.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImagesService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<String> getImagesByMaTour(String maTour) {
        // Truy vấn từ bảng Images để lấy danh sách các ID
        Query query = entityManager.createQuery("SELECT i.id FROM Images i WHERE i.maTour = :maTour");
        query.setParameter("maTour", maTour);
        List<Integer> idList = query.getResultList();

        // Sử dụng danh sách ID để truy vấn từ bảng HinhAnh và lấy các hình ảnh tương ứng
        Query hinhAnhQuery = entityManager.createQuery("SELECT h.img FROM HinhAnh h WHERE h.id IN :idList");
        hinhAnhQuery.setParameter("idList", idList);
        return hinhAnhQuery.getResultList();
    }
}