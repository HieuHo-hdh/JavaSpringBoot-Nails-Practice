package com.landingis.api.dto.collaboratorProduct;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.collaborator.CollaboratorDto;
import com.landingis.api.dto.product.ProductDto;
import lombok.Data;

@Data
public class CollaboratorProductDto extends ABasicAdminDto {
    private Integer kind;
    private Double value;
    private CollaboratorDto collaboratorDto;
    private ProductDto productDto;
}
