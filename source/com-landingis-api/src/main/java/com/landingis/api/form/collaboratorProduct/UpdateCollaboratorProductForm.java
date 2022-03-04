package com.landingis.api.form.collaboratorProduct;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateCollaboratorProductForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(name = "id")
    private Long id;

    @NotNull(message = "kind cannot be null")
    @ApiModelProperty(name = "kind")
    private Integer kind;

    @NotNull(message = "value cannot be null")
    @ApiModelProperty(name = "value")
    private Double value;
}
