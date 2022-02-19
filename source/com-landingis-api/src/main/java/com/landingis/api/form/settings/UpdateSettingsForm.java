package com.landingis.api.form.settings;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateSettingsForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @ApiModelProperty(name="description")
    private String description;

    @NotEmpty(message = "settingsValue cannot be null")
    @ApiModelProperty(name="settingValue", required = true)
    private String settingsValue;

    @Status
    @NotNull(message = "Status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;
}
