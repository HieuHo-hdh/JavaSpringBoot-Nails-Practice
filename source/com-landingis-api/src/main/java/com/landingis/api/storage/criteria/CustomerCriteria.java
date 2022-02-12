package com.landingis.api.storage.criteria;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Customer;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerCriteria  {
    private Long id;
    private String email;
    private String fullName;
    private String phone;

    public Specification<Customer> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //Creates a WHERE clause for a query of the referenced entity in form of a Predicate for the given Root and CriteriaQuery.
                List<Predicate> predicates = new ArrayList<>();
                if (getId() != null) {
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                Join<Customer, Account> joinAccount = root.join("account", JoinType.INNER);
                if (!StringUtils.isEmpty(getEmail()))
                {
                    predicates.add(cb.like(cb.lower(joinAccount.get("email")),"%" + getEmail().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getFullName()))
                {
                    predicates.add(cb.like(cb.lower(joinAccount.get("fullName")), "%" + getFullName().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getPhone()))
                {
                    predicates.add(cb.like(cb.lower(joinAccount.get("phone")), "%" + getPhone().toLowerCase() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
