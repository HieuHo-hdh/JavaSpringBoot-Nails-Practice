package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Category;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryCriteria {
    private Long id;
    private Integer kind;
    private String name;
    private Long parentId;
    private Integer status;

    public Specification<Category> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
//                Join<Category, Category> joinCategory = root.join("parentCategory", JoinType.INNER);

                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }

                if(!StringUtils.isEmpty(getName())) {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + getName().toLowerCase() + "%"));
                }

                if(getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }

                if(getParentId() != null) {
                    predicates.add(cb.equal(root.get("parentCategory"), getParentId()));
                }
                else {
                    predicates.add(cb.isNull(root.get("parentCategory")));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
