package com.landingis.api.form.orders;

import com.landingis.api.dto.orders.OrdersDetailDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrdersDetailForm {
    @NotNull(message="amount cannot be null")
    @ApiModelProperty(name = "amount", required = true)
    private Integer amount;

    @NotNull(message="productId cannot be null")
    @ApiModelProperty(name = "product_Id", required = true)
    private Long productId;

    @ApiModelProperty(name = "note")
    private String note;
}
