package com.landingis.api.form.importExport;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateImportExportForm {
    @NotNull(message = "id cannot be null")
    @ApiModelProperty(name = "id")
    private Long id;

    @ApiModelProperty(name = "code")
    private String code;
    @ApiModelProperty(name = "filePath")
    private String filePath;

    @NotNull(message = "money cannot be null")
    @ApiModelProperty(name = "money")
    private Double money;
    @ApiModelProperty(name = "note")
    private String note;
}
