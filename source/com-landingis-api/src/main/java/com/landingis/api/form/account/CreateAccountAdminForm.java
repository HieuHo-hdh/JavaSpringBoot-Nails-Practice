package com.landingis.api.form.account;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel
public class CreateAccountAdminForm {
    @NotEmpty(message = "username cant not be null")
    @ApiModelProperty(name = "username", required = true)
    private String username;
    @ApiModelProperty(name = "email")
    @Email
    private String email;
    @NotEmpty(message = "password cant not be null")
    @ApiModelProperty(name = "password", required = true)
    private String password;
    @NotEmpty(message = "fullName cant not be null")
    @ApiModelProperty(name = "fullName",required = true)
    private String fullName;
    private String avatarPath;

    @Status
    private Integer status = 1;
    @NotEmpty(message = "phone can not be empty")
    @ApiModelProperty(name = "phone")
    private String phone;

    @NotNull(message = "kind cannot be null")
    @ApiModelProperty(name = "kind", required = true)
    private Integer kind;
}
