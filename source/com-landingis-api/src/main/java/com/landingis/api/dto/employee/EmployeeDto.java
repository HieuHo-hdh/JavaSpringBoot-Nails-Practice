package com.landingis.api.dto.employee;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDto extends ABasicAdminDto {
    private String employeeEmail;
    private String employeeFullName;
    private String employeePhone;
    private String employeeUserName;
    private String employeeAvatarPath;
    private String employeeAddress;


    private Date birthday;
    private Integer sex;
    private String note;

    private String identityNumber;
    private Date dateOfIssue;
    private String placeOfIssue;

    private String bankNo;
    private String bankName;
    private String branchName;

    private String labelColor;
    private Double salary;

}
