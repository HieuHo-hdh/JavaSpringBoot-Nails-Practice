package com.landingis.api.storage.criteria;

import com.landingis.api.dto.ErrorCode;
import com.landingis.api.exception.RequestException;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.ImportExport;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ImportExportCriteria {
    private Long id;
    private Long categoryId;
    private String code;
    private Date from;
    private Date to;
//    @NotNull(message = "kind can not be null")
//    @ApiModelProperty(name = "kind", required = true)
    private Integer kind;

    public Specification<ImportExport> getSpecification() {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<ImportExport> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(getId() != null){
                    predicates.add(cb.equal(root.get("id"), getId()));
                }
                if(getCategoryId() != null){
                    Join <ImportExport, Category> joinCategory = root.join("category", JoinType.INNER);
                    predicates.add(cb.equal(joinCategory.get("id"), getCategoryId()));
                }
                if(!StringUtils.isEmpty(getCode())){
                    predicates.add(cb.like(cb.lower(root.get("code")), "%"+getCode().toLowerCase()+"%"));
                }

                if(getFrom() != null) {
                    //greaterThanOrEqualTo(a, b):
                    //Create a predicate for testing whether the first argument is greater than or equal to the second.
                    //Test if a >= b
                    predicates.add(cb.greaterThanOrEqualTo(root.get("createdDate"), getFrom()));
                }
                if(getTo() != null)
                {
                    //predicates.add(cb.greaterThanOrEqualTo(getFrom(), root.get("createdDate")));

                    //lessThanOrEqualTo(a, b):
                    //Create a predicate for testing whether the first argument is less than or equal to the second.
                    //Test if a <= b
                    predicates.add(cb.lessThanOrEqualTo(root.get("createdDate"), getTo()));
                }
                if(getKind() != null) {
                    predicates.add(cb.equal(root.get("kind"), getKind()));
                }
                else throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_NULL_KIND, "Kind can not be null");

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
