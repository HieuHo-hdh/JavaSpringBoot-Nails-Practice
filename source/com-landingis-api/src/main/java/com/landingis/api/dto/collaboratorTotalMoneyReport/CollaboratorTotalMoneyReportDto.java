package com.landingis.api.dto.collaboratorTotalMoneyReport;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CollaboratorTotalMoneyReportDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "collaborator_id")
    private Long collaboratorId;

    @ApiModelProperty(name = "collaborator_full_name")
    private String collaboratorFullName;

    @ApiModelProperty(name = "total_money")
    private Double totalMoney = 0d;
}
