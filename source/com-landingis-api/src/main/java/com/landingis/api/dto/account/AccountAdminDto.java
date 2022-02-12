package com.landingis.api.dto.account;

import com.landingis.api.dto.group.GroupDto;
import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class AccountAdminDto extends ABasicAdminDto {

    @ApiModelProperty(name = "kind")
    private Integer kind;
    @ApiModelProperty(name = "username")
    private String username;
    @ApiModelProperty(name = "email")
    private String email;
    @ApiModelProperty(name = "fullName")
    private String fullName;
    @ApiModelProperty(name = "group")
    private GroupDto group;
    @ApiModelProperty(name = "lastLogin")
    private Date lastLogin;
    @ApiModelProperty(name = "avatar")
    private String avatar;
    @ApiModelProperty(name = "phone")
    private String phone;
    @ApiModelProperty(name = "labelColor")
    private String labelColor;
    @ApiModelProperty(name = "salary")
    private Double salary;
}
