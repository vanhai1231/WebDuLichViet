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
        if (fieldName.equals("tenTour")) {
            return criteriaBuilder.like(root.get(fieldName), "%" + value + "%");
        } else if (fieldName.equals("noiKhoiHanh")) {
            return criteriaBuilder.equal(root.get(fieldName), value);
        } else if (fieldName.equals("ngayKH")) {
            return criteriaBuilder.equal(root.get(fieldName), (Date) value);
        } else if (fieldName.equals("giaTour")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), (Integer) value);
        }
        return null;
    }

    public static Specification<Tour> withField(String fieldName, Object value) {
        return new TourSpecification(fieldName, value);
    }
}
