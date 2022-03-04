package com.landingis.api.form.collaboratorProduct;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCollaboratorProductForm {
    @NotNull(message = "collaboratorId cannot be null")
    @ApiModelProperty(name = "collaboratorId")
    private Long collaboratorId;

    @NotNull(message = "productId cannot be null")
    @ApiModelProperty(name = "productId")
    private Long productId;

    @NotNull(message = "kind cannot be null")
    @ApiModelProperty(name = "kind")
    private Integer kind;

    @NotNull(message = "value cannot be null")
    @ApiModelProperty(name = "value")
    private Double value;
}
