package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Province;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProvinceCriteria {
    private Long id;
    private String name;
    private String kind;
    private Long parentId;

    public Specification<Province> getSpecification()
    {
        return new Specification<Province>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Province> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(!StringUtils.isEmpty(getName())) {
                    predicates.add(cb.like(cb.lower(root.get("provinceName")), "%" + getName().toLowerCase() + "%"));
                }

                if (getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }

                if(getParentId() != null) {
                    Join<Province, Province> joinParentProvince = root.join("parentProvince", JoinType.INNER);
                    predicates.add(cb.equal(joinParentProvince.get("id"), getParentId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
