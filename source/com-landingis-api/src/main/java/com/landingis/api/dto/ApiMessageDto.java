package com.landingis.api.dto;

import lombok.Data;

@Data
public class ApiMessageDto<T> {
    private Boolean result = true;
    private String code = null;
    private T data = null;
    private String message = null;

}
