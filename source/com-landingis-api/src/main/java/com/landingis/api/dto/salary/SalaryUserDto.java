package com.landingis.api.dto.salary;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;

public class SalaryUserDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "fullName")
    private String fullName;
}
