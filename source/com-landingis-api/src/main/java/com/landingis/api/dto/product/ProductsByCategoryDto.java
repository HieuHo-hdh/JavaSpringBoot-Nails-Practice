package com.landingis.api.dto.product;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductsByCategoryDto extends ABasicAdminDto {
    private String categoryName;
    private String categoryImage;
    private List<ProductDto> productList;
}
