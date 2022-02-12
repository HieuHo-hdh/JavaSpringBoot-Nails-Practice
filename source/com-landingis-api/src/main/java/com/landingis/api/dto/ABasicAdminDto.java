package com.landingis.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "status")
    private Integer status;

    @ApiModelProperty(name = "modifiedDate")
    private Date modifiedDate;

    @ApiModelProperty(name = "createdDate")
    private Date createdDate;

    @ApiModelProperty(name = "modifiedBy")
    private String modifiedBy;

    @ApiModelProperty(name = "createdBy")
    private String createdBy;
}
