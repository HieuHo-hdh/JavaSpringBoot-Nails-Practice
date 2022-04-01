package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Salary;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class SalaryCriteria {
    private Long id;
    private Integer userKind;
    private Integer salaryPeriodId;

    public Specification<Salary> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Salary> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(getSalaryPeriodId() != null) {
                    predicates.add(cb.equal(root.get("salaryPeriodId"), getSalaryPeriodId()));
                }

                if(getUserKind() != null) {
                    predicates.add(cb.equal(root.get("userKind"), getUserKind()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
