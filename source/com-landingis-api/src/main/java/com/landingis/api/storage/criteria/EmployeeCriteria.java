package com.landingis.api.storage.criteria;

import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Employee;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeCriteria {
    private Long id;
    private String email;
    private String fullName;
    private String phone;

    public Specification<Employee> getSpecification()
    {
        return new Specification<Employee>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                if (getId() != null)
                {
                    predicateList.add(criteriaBuilder.equal(root.get("Id"), getId()));
                }
                Join<Employee, Account> joinAccount = root.join("account", JoinType.INNER);
                if (!StringUtils.isEmpty(getEmail()))
                {
                    predicateList.add(criteriaBuilder.like(
                            criteriaBuilder.lower(joinAccount.get("email")),
                            "%" + getEmail().toLowerCase() + "%")
                    );
                }

                if (!StringUtils.isEmpty(getFullName()))
                {
                    predicateList.add(criteriaBuilder.like(
                            criteriaBuilder.lower(joinAccount.get("fullName")),
                            "%" + getFullName().toLowerCase() + "%")
                    );
                }

                if (!StringUtils.isEmpty(getPhone()))
                {
                    predicateList.add(criteriaBuilder.like(
                            criteriaBuilder.lower(joinAccount.get("phone")),
                            "%" + getPhone().toLowerCase() + "%")
                    );
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
