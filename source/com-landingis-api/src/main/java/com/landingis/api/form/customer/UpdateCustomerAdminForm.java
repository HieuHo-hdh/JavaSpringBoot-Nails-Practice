package com.landingis.api.form.customer;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateCustomerAdminForm {

    @NotNull(message="id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(name="customerEmail")
    private String customerEmail;

    @NotEmpty(message = "customerFullName cannot be null")
    @ApiModelProperty(required = true)
    private String customerFullName;

    @ApiModelProperty(name = "customerPassword")
    private String customerPassword;

    @NotEmpty(message = "customerPhone cannot be null")
    @ApiModelProperty(required = true)
    private String customerPhone;

    @ApiModelProperty(name = "customerAvatarPath")
    private String customerAvatarPath;

    @ApiModelProperty(name = "customerAddress")
    private String customerAddress;

    @NotNull(message = "birthDate cannot be null")
    @ApiModelProperty(required = true)
    private Date birthday;

    @NotNull(message = "sex cannot be null")
    @ApiModelProperty(required = true)
    private Integer sex;

    private String note;

    @Status
    @NotNull(message = "status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;

//    @NotEmpty(message = "isLoyalty cannot be null")
//    @ApiModelProperty(required = true)
    private Boolean isLoyalty;
    private Date loyaltyDate;
    private Integer loyaltyLevel;
    private Integer saleOff;
}
