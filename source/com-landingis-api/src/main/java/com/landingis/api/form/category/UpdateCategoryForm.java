package com.landingis.api.form.category;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCategoryForm {

    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @NotEmpty(message = "categoryName cannot be null")
    @ApiModelProperty(required = true)
    private String categoryName;

    @NotEmpty(message = "categoryDescription cannot be null")
    @ApiModelProperty(required = true)
    private String categoryDescription;

    @ApiModelProperty(required = true)
    private String categoryImage;

    @ApiModelProperty(name = "categoryOrdering")
    private Integer categoryOrdering;

    @Status
    @NotNull(message = "status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;

}
