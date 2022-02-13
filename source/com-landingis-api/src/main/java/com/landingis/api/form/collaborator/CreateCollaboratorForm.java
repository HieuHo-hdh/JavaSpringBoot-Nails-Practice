package com.landingis.api.form.collaborator;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class CreateCollaboratorForm {
    @ApiModelProperty(name = "collaboratorEmail")
    private String collaboratorEmail;

    @NotEmpty(message = "collaboratorFullName cannot be null")
    @ApiModelProperty(name = "collaboratorFullName")
    private String collaboratorFullName;

    @NotEmpty(message = "collaboratorUsername cannot be null")
    @ApiModelProperty(name = "collaboratorUsername")
    private String collaboratorUsername;

    @NotEmpty(message = "password cant not be null")
    @ApiModelProperty(name = "password", required = true)
    private String password;

    @NotEmpty(message = "collaboratorPhone cannot be null")
    @ApiModelProperty(name = "collaboratorPhone")
    private String collaboratorPhone;

    @ApiModelProperty(name = "collaboratorAvatarPath")
    private String collaboratorAvatarPath;

    @NotEmpty(message = "collaboratorAddress cannot be null")
    @ApiModelProperty(name = "collaboratorAddress")
    private String collaboratorAddress;

    @NotNull(message = "birthday cannot be null")
    @ApiModelProperty(required = true)
    private Date birthday;

    @NotNull(message = "sex cannot be null")
    @ApiModelProperty(required = true)
    private Integer sex;

    private String note;

    private String identityNumber;
    private Date dateOfIssue;
    private String placeOfIssue;

    private String bankNo;
    private String bankName;
    private String branchName;

    @Status
    @NotNull(message = "Status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;
}
