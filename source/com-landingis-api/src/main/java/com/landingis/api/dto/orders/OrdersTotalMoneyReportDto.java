package com.landingis.api.dto.orders;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.collaborator.CollaboratorDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrdersTotalMoneyReportDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "orders_id")
    private Long ordersId;

//    @ApiModelProperty(name = "collaborator_full_name")
    private String collaboratorFullName;

    @ApiModelProperty(name = "total_money")
    private Double totalMoney = 0d;
}
