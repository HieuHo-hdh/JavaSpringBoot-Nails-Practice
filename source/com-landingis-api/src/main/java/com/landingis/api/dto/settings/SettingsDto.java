package com.landingis.api.dto.settings;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SettingsDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "name")
    private String name;
    @ApiModelProperty(name = "description")
    private String description;
    @ApiModelProperty(name = "editable")
    private Boolean editable;
    @ApiModelProperty(name = "settingsGroup")
    private String settingsGroup;
    @ApiModelProperty(name = "settingsKey")
    private String settingsKey;
    @ApiModelProperty(name = "settingsValue")
    private String settingsValue;
    @ApiModelProperty(name = "kind")
    private Integer kind;
    @ApiModelProperty(name = "groupId")
    private Long groupId;

}
