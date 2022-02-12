package com.landingis.api.form.employee;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateEmployeeAdminForm {
    @NotNull(message="id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(name = "employeeEmail")
    private String employeeEmail;

    @NotEmpty(message = "employeeFullName cannot be null")
    @ApiModelProperty(name = "employeeFullName")
    private String employeeFullName;

    @NotEmpty(message = "employeeUsername cannot be null")
    @ApiModelProperty(name = "employeeUsername")
    private String employeeUsername;

    @ApiModelProperty(name = "password", required = true)
    private String password;

    @NotEmpty(message = "employeePhone cannot be null")
    @ApiModelProperty(name = "employeePhone")
    private String employeePhone;

    @ApiModelProperty(name = "employeeAvatarPath")
    private String employeeAvatarPath;

    @NotEmpty(message = "employeeAddress cannot be null")
    @ApiModelProperty(name = "employeeAddress")
    private String employeeAddress;

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

    private String labelColor;

    @NotNull(message = "Salary cannot be null")
    @ApiModelProperty(required = true)
    private Double salary;

    @Status
    @NotNull(message = "Status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;
}
