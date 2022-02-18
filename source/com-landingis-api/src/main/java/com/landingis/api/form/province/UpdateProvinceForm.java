package com.landingis.api.form.province;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateProvinceForm {
    @NotNull(message="id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @NotEmpty(message = "provinceName cannot be null")
    @ApiModelProperty(name="provinceName", required = true)
    private String provinceName;

    private Integer status;

}
