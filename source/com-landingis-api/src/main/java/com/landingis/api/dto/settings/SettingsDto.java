package com.landingis.api.dto.settings;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SettingsDto extends ABasicAdminDto {
    private Long id;
    private String name;
    private String description;
    private Boolean editable;

    @ApiModelProperty(name = "settingsGroup")
    private String settingsGroup;
    @ApiModelProperty(name = "settingsKey")
    private String settingsKey;

    @ApiModelProperty(name = "settingsValue")
    private String settingsValue;

    private Integer kind;
    private Long groupId;

}
