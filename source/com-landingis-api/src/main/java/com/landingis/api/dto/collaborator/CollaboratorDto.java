package com.landingis.api.dto.collaborator;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

import java.util.Date;

@Data
public class CollaboratorDto extends ABasicAdminDto {
    private String collaboratorEmail;
    private String collaboratorFullName;
    private String collaboratorPhone;
    private String collaboratorUserName;
    private String collaboratorAvatarPath;
    private String collaboratorAddress;

    private Date birthday;
    private Integer sex;
    private String note;

    private String identityNumber;
    private Date dateOfIssue;
    private String placeOfIssue;

    private String bankNo;
    private String bankName;
    private String branchName;

    private Long employeeId;
}
