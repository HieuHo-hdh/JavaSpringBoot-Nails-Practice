package com.landingis.api.form.salaryPeriod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateSalaryPeriodForm {
    @NotNull(message = "startDate cant not be null")
    @ApiModelProperty(name = "startDate", required = true)
    private Date startDate;

    @NotNull(message = "endDate cant not be null")
    @ApiModelProperty(name = "endDate", required = true)
    private Date endDate;
}
