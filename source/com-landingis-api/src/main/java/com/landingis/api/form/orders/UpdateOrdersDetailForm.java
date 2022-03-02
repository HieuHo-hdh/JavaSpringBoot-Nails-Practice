package com.landingis.api.form.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateOrdersDetailForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @NotNull(message="amount cannot be null")
    @ApiModelProperty(name = "amount", required = true)
    private Integer amount;

    @NotNull(message="productId cannot be null")
    @ApiModelProperty(name = "product_Id", required = true)
    private Long productId;

    @NotNull(message="ordersId cannot be null")
    @ApiModelProperty(name = "order_Id", required = true)
    private Long ordersId;

    @ApiModelProperty(name = "note")
    private String note;
}
