package com.landingis.api.dto.province;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class ProvinceDto extends ABasicAdminDto {
    private Long id;
    private String provinceName;
    private String kind;
    private Long parentId;
}
