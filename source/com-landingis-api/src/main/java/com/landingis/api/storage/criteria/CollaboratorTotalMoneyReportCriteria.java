package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Collaborator;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CollaboratorTotalMoneyReportCriteria {
    private Date from;
    private Date to;
    public Specification<Collaborator> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Collaborator> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
//                if (getFrom() != null) {
//                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), getFrom()));
//                }
//                if (getTo() != null) {
//                    predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), getTo()));
//                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));

            }
        };
    }
}
