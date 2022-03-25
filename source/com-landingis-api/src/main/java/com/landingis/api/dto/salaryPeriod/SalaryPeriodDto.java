package com.landingis.api.dto.salaryPeriod;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SalaryPeriodDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "start_date")
    private Date startDate;

    @ApiModelProperty(name = "end_date")
    private Date endDate;

    @ApiModelProperty(name = "state")
    private Integer state;

}
