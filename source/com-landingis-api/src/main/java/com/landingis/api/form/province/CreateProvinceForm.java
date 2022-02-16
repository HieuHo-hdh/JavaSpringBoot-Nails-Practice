package com.landingis.api.form.province;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateProvinceForm {

    @NotEmpty(message = "provinceName cannot be null")
    @ApiModelProperty(name="provinceName", required = true)
    private String provinceName;

//    @ApiModelProperty(name = "parentId")
    private Long parentId;
    private Integer kind;
}
