package com.hutech.DAMH.specification;

import com.hutech.DAMH.model.Tour;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.*;

import java.util.Date;

public class TourSpecification implements Specification<Tour> {

    private String fieldName;
    private Object value;

    public TourSpecification(String fieldName, Object value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Tour> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (value == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Always true, no filtering
        }

        switch (fieldName) {
            case "loaiTour.maLoaiTour":
                Join<Object, Object> loaiTourJoin = root.join("loaiTour");
                return criteriaBuilder.equal(loaiTourJoin.get("maLoaiTour"), value);
            case "noiKhoiHanh":
                return criteriaBuilder.equal(root.get(fieldName), value);
            case "ngayKH":
                return criteriaBuilder.equal(root.get(fieldName), (Date) value);
            case "giaTour":
                return criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), (Integer) value);
            case "phuongTien.maPhuongTien":
                Join<Object, Object> phuongTienJoin = root.join("phuongTien");
                return criteriaBuilder.equal(phuongTienJoin.get("maPhuongTien"), value);
            case "promotionActive":
                return criteriaBuilder.equal(root.get(fieldName), (Boolean) value);
            default:
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // No filtering
        }
    }

    public static Specification<Tour> withField(String fieldName, Object value) {
        return new TourSpecification(fieldName, value);
    }
}
