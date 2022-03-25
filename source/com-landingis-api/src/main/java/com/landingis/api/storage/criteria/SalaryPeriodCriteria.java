package com.landingis.api.storage.criteria;
import com.landingis.api.storage.model.SalaryPeriod;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class SalaryPeriodCriteria {
    private Long id;
    private Integer state;

    public Specification<SalaryPeriod> getSpecification() {
        return new Specification<SalaryPeriod>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<SalaryPeriod> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getState() != null){
                    predicates.add(cb.equal(root.get("state"), getState()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
