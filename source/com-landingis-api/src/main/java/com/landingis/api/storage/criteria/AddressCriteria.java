package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Address;
import com.landingis.api.storage.model.Customer;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class AddressCriteria {
//    private Long id;
//    private Long customerId;

    private String name;
    private String phone;
//    private String address;

    public Specification<Address> getSpecification()
    {
        return new Specification<Address>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //Có cần connect Address với Customer
//              if (getId() != null) {
//                  predicates.add(cb.equal(root.get("id"), getId()));
//              }
//              Join<Customer, Account> joinAccount = root.join("account", JoinType.INNER);
                if (!StringUtils.isEmpty(getPhone()))
                {
                    predicates.add(cb.like(cb.lower(root.get("phone")),"%" + getPhone().toLowerCase() + "%"));
                }
                if (!StringUtils.isEmpty(getName()))
                {
                    predicates.add(cb.like(cb.lower(root.get("name")), "%" + getName().toLowerCase() + "%"));
                }
//                if (!StringUtils.isEmpty(getAddress()))
//                {
//                    predicates.add(cb.like(cb.lower(root.get("address")), "%" + getAddress().toLowerCase() + "%"));
//                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
