package com.landingis.api.dto.group;

import java.util.List;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.permission.PermissionDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GroupAdminDto extends ABasicAdminDto {

    @ApiModelProperty(name = "name")
    private String name;
    @ApiModelProperty(name = "description")
    private String description;
    @ApiModelProperty(name = "kind")
    private int kind;
    @ApiModelProperty(name = "permissions")
    private List<PermissionDto> permissions;
}
