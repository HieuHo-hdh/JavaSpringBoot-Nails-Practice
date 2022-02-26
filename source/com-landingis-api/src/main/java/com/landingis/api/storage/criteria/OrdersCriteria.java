package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrdersCriteria {
    private Long id;
    private String code;
    private Integer state;
    private Date from;
    private Date to;
    private Long collaboratorId;
    private Long employeeId;
    private Long customerId;
    private String employeeFullName;
    private String customerPhone;
    private String collaboratorFullName;
    public Specification<Orders> getSpecification() {
        return new Specification<Orders>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Orders> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if (!StringUtils.isEmpty(getCode()))
                {
                    predicates.add(cb.like(cb.lower(root.get("code")),"%" + getCode().toLowerCase() + "%"));
                }
                if (getState() != null) {
                    predicates.add(cb.equal(root.get("state"), getState()));
                }
                if(getFrom() != null) {
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), getFrom()));
                }
                if(getTo() != null)
                {
                    predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), getTo()));
                }
                if(getCollaboratorId () != null){
                    Join <Orders, Collaborator> joinCollaborator = root.join("collaborator", JoinType.INNER);
                    predicates.add(cb.equal(joinCollaborator.get("id"), getCollaboratorId()));
                }
                if(getCustomerId () != null){
                    Join <Orders, Customer> joinCustomer = root.join("customer", JoinType.INNER);
                    predicates.add(cb.equal(joinCustomer.get("id"), getCustomerId()));
                }
                if(getEmployeeId () != null){
                    Join <Orders, Employee> joinEmployee = root.join("employee", JoinType.INNER);
                    predicates.add(cb.equal(joinEmployee.get("id"), getEmployeeId()));
                }
                if(!StringUtils.isEmpty(getCollaboratorFullName())) {
                    Join <Orders, Collaborator> joinCollaborator = root.join("collaborator", JoinType.INNER);
                    Join <Join<Orders, Collaborator>, Account> joinAccountJoin = joinCollaborator.join("account", JoinType.INNER);
                    predicates.add(cb.like(cb.lower(joinAccountJoin.get("name")), "%" + getCollaboratorFullName().toLowerCase() + "%"));
                }
                if(!StringUtils.isEmpty(getEmployeeFullName())) {
                    Join <Orders, Employee> joinEmployeee = root.join("employee", JoinType.INNER);
                    Join <Join<Orders, Employee>, Account> joinAccountJoin = joinEmployeee.join("account", JoinType.INNER);
                    predicates.add(cb.like(cb.lower(joinAccountJoin.get("name")), "%" + getEmployeeFullName().toLowerCase() + "%"));
                }
                if(!StringUtils.isEmpty(getCustomerPhone())) {
                    Join <Orders, Customer> joinCustomer = root.join("customer", JoinType.INNER);
                    Join <Join<Orders, Employee>, Account> joinAccountJoin = joinCustomer.join("account", JoinType.INNER);
                    predicates.add(cb.like(cb.lower(joinAccountJoin.get("phone")), "%" + getCustomerPhone().toLowerCase() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
