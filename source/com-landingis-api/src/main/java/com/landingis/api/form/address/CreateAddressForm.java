package com.landingis.api.form.address;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateAddressForm {
    @NotNull(message="customerId cannot be null")
    @ApiModelProperty(required = true)
    private Long customerId;

    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(name="name", required = true)
    private String name;

    @NotEmpty(message = "phone cannot be null")
    @ApiModelProperty(name="phone", required = true)
    private String phone;

    @NotEmpty(message = "address cannot be null")
    @ApiModelProperty(name="address", required = true)
    private String address;

    @NotNull(message="provinceId cannot be null")
    @ApiModelProperty(required = true)
    private Long provinceId;

    @NotNull(message="districtId cannot be null")
    @ApiModelProperty(required = true)
    private Long districtId;

    @NotNull(message="communeId cannot be null")
    @ApiModelProperty(required = true)
    private Long communeId;
}
