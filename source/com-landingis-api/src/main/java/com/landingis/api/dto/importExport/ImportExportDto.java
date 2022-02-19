package com.landingis.api.dto.importExport;

import com.landingis.api.dto.ABasicAdminDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ImportExportDto extends ABasicAdminDto {
    @ApiModelProperty(name = "id")
    private Long id;
    @ApiModelProperty(name = "categoryId")
    private Long categoryId;

    @ApiModelProperty(name = "code")
    private String code;

    @ApiModelProperty(name = "filePath")
    private String filePath;

    @ApiModelProperty(name = "money")
    private Double money;

    @ApiModelProperty(name = "note")
    private String note;
    @ApiModelProperty(name = "kind")
    private Integer kind;
}
