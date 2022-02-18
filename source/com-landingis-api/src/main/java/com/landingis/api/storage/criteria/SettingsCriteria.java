package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Settings;
import com.landingis.api.storage.model.Settings;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Data
public class SettingsCriteria {
    public Specification<Settings> getSpecification()
    {
        return new Specification<Settings>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Settings> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
