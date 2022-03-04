package com.landingis.api.form.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateOrdersStateForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

//    @NotNull(message = "ordersState cannot be null")
//    @ApiModelProperty(required = true)
    @ApiModelProperty(name="ordersState")
    private Integer ordersState;
}
