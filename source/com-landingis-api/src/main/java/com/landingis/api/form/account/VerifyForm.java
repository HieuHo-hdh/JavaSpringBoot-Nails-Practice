package com.landingis.api.form.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class VerifyForm {
    @NotNull(message = "id can not be empty")
    @ApiModelProperty(name = "id")
    private Long id;
    @NotEmpty(message = "otp can not be empty")
    @ApiModelProperty(name = "otp")
    private String otp;
}
