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

    @NotEmpty(message = "name cannot be null")
    @ApiModelProperty(name="name", required = true)
    private String name;

    private String description;
    private Boolean editable;

    @NotEmpty(message = "settingsGroup cannot be null")
    @ApiModelProperty(name="settingsGroup", required = true)
    private String settingsGroup;

    @NotEmpty(message = "settingsKey cannot be null")
    @ApiModelProperty(name="settingsKey", required = true)
    private String settingsKey;

    @NotEmpty(message = "settingsValue cannot be null")
    @ApiModelProperty(name="settingValue", required = true)
    private String settingsValue;

    @NotNull(message = "kind cannot be null")
    @ApiModelProperty(name="kind", required = true)
    private Integer kind;

    @NotNull(message = "groupId cannot be null")
    @ApiModelProperty(name="groupId", required = true)
    private Long groupId;

    @Status
    @NotNull(message = "Status cannot be null")
    @ApiModelProperty(required = true)
    private Integer status;
}
