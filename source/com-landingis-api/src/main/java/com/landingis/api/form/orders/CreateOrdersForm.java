package com.landingis.api.form.orders;

import com.landingis.api.dto.orders.OrdersDetailDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrdersForm {
    @ApiModelProperty(name = "customer_email")
    private String customerEmail;

    @NotEmpty(message="customerFullName cannot be null")
    @ApiModelProperty(name = "customer_full_name", required = true)
    private String customerFullName;

    @NotEmpty(message="customerPhone cannot be null")
    @ApiModelProperty(name = "customer_phone", required = true)
    private String customerPhone;

    @ApiModelProperty(name = "orders_address")
    private String ordersAddress;

    @ApiModelProperty(name = "orders_document")
    private String ordersDocument;

    @ApiModelProperty(name = "orders_sale_off")
    private Integer ordersSaleOff;

    @NotEmpty(message = "ordersDetailDtoList cannot be null")
    @ApiModelProperty(required = true)
    private List<@Valid CreateOrdersDetailForm> ordersDetailDtos;
}
