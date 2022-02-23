package com.landingis.api.dto.product;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

import java.util.List;

@Data
public class ProductDto extends ABasicAdminDto {
    private String name;
    private Double price;
    private String image;

    private String description;
    private String shortDescription;

    private Integer parentId;
    private Boolean hasChild;
    private String labelColor;
    private Integer saleOff;
    private Long categoryId;

    //productChilds: list of child products whose datatype is ProductDto
    private List<ProductDto> productChilds;


}
