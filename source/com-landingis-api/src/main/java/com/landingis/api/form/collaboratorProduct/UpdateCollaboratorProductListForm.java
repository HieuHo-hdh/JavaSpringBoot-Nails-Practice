package com.landingis.api.form.collaboratorProduct;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class UpdateCollaboratorProductListForm {
    @NotEmpty(message = "collaboratorProducts cannot be empty")
    @ApiModelProperty(required = true)
    List<@Valid UpdateCollaboratorProductForm> collaboratorProducts;
}
