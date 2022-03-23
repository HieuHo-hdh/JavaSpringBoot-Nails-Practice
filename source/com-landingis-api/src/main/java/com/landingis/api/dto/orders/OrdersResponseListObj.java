package com.landingis.api.dto.orders;

import com.landingis.api.dto.ResponseListObj;
import lombok.Data;

@Data
public class OrdersResponseListObj <T> extends ResponseListObj<T> {
    private Double sum;
}

