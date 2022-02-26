package com.landingis.api.dto.orders;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.product.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDetailDto extends ABasicAdminDto {
    private Long id;
    private Long ordersId;

    private Integer kind;
    private Integer amount;
    private Double price;
    private Integer value;
    private Integer collaboratorCommission;
    private String note;

    private List<ProductDto> productList;
}
