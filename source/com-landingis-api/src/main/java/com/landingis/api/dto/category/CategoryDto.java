package com.landingis.api.dto.category;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.storage.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto extends ABasicAdminDto {
    private String categoryName;
    private String categoryDescription;
    private String categoryImage;
    private Integer categoryOrdering;
    private Integer categoryKind;
    private Integer parentId;
}
