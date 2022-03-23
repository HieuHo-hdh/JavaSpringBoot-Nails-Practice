package com.landingis.api.form.orders;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CreateOrdersForm {
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

    @ApiModelProperty(name = "ordersDocument")
    private String ordersDocument;

    @ApiModelProperty(name = "ordersSaleOff")
    private Integer ordersSaleOff;

    @NotNull(message="collaboratorId cannot be null")
    @ApiModelProperty(name = "collaborator_Id", required = true)
    private Long collaboratorId;

    @NotEmpty(message = "ordersDetailDtoList cannot be null")
    @ApiModelProperty(required = true)
    private List<@Valid CreateOrdersDetailForm> ordersDetailDtos;
}
