package com.landingis.api.dto.salary;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class SalaryDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "salary_period_id")
    private Long salaryPeriodId;

    @ApiModelProperty(name = "revenue")
    private Double revenue;

    @ApiModelProperty(name = "commission")
    private Double commission;

    @ApiModelProperty(name = "salaryPrice")
    private Double salaryPrice;

    @ApiModelProperty(name = "is_payment")
    private Boolean isPayment;

    @ApiModelProperty(name = "pay_date")
    private Date payDate;

    private SalaryUserDto salaryUserDto;
}
