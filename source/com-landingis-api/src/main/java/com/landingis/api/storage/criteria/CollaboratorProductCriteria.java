package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Collaborator;
import com.landingis.api.storage.model.CollaboratorProduct;
import com.landingis.api.storage.model.Product;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CollaboratorProductCriteria {
    private Long id;
    private Long collaboratorId;
    private String productName;
    private Integer kind;
    private Integer status;

    public Specification<CollaboratorProduct> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<CollaboratorProduct> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }

                if(getCollaboratorId() != null) {
                    Join<CollaboratorProduct, Collaborator> joinCollaborator =root.join("collaborator", JoinType.INNER);
                    predicates.add(cb.equal(joinCollaborator.get("id"), getCollaboratorId()));
                }

                if(!StringUtils.isEmpty(getProductName())) {
                    Join<CollaboratorProduct, Product> joinProduct = root.join("product", JoinType.INNER);
                    predicates.add(cb.like(cb.lower(joinProduct.get("name")), "%" + getProductName().toLowerCase() + "%"));
                }

                if(getStatus() != null) {
                    predicates.add(cb.equal(root.get("status"), getStatus()));
                }

                if(getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
