package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductCriteria {
    //search theo categoryId, status
    private Long id;
    private Long categoryId;
    private String name;
    private Long parentId;
    private Integer status;
    
    public Specification<Product> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if (!StringUtils.isEmpty(getName()))
                {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + getName().toLowerCase() + "%"));
                }

                if(getParentId() != null) {
                    //Join <child, parent> = join (parent)
                    Join<Product, Product> joinProduct =root.join("parentProduct", JoinType.INNER);
                    predicates.add(cb.equal(joinProduct.get("id"), getParentId()));
                }
                else {
                    predicates.add(cb.isNull(root.get("parentProduct")));
                }

                if(getCategoryId() != null) {
                    //Join <child, parent> = join (parent)
                    Join<Product, Product> joinCategory =root.join("category", JoinType.INNER);
                    predicates.add(cb.equal(joinCategory.get("id"), getCategoryId()));
                }

                if(getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
