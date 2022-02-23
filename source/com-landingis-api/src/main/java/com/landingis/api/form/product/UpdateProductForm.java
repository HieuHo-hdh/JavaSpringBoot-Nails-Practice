package com.landingis.api.form.product;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProductForm {

    @NotNull(message = "id cannot be null")
    @ApiModelProperty(name = "id")
    private Long id;

    @NotNull(message = "categoryId cannot be null")
    @ApiModelProperty(required = true)
    private Long categoryId;

    @NotEmpty(message = "productName cannot be null")
    @ApiModelProperty(required = true)
    private String productName;

    @NotNull(message = "productPrice cannot be null")
    @ApiModelProperty(required = true)
    private Double productPrice;

    @ApiModelProperty(name = "productImage")
    private String productImage;

    @ApiModelProperty(name = "description")
    private String description;

    @ApiModelProperty(name = "shortDescription\t")
    private String shortDescription	;

    @ApiModelProperty(name = "labelColor")
    private String labelColor;

    @ApiModelProperty(name = "saleOff")
    private Integer saleOff;

    @Status
    @NotNull(message = "status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;
}
