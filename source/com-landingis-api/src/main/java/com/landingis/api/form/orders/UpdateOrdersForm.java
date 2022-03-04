package com.landingis.api.form.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class UpdateOrdersForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(name = "customerEmail")
    private String customerEmail;

    @NotEmpty(message="customerFullName cannot be null")
    @ApiModelProperty(name = "customerFullName", required = true)
    private String customerFullName;

    @NotEmpty(message="customerPhone cannot be null")
    @ApiModelProperty(name = "customerPhone", required = true)
    private String customerPhone;

    @ApiModelProperty(name = "ordersAddress")
    private String ordersAddress;

    @ApiModelProperty(name = "ordersSaleOff")
    private Integer ordersSaleOff;

    @NotEmpty(message = "ordersDetailDtoList cannot be null")
    @ApiModelProperty(required = true)
    private List<@Valid UpdateOrdersDetailForm> ordersDetailDtos;

//    @NotEmpty(message = "ordersDetailDtoList cannot be null")
    @ApiModelProperty(name="deletingOrdersDetailsDtos")
    private List<@Valid UpdateOrdersDetailForm> deletingOrdersDetailsDtos;
}
