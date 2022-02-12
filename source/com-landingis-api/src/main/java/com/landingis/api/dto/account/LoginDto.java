package com.landingis.api.dto.account;

import lombok.Data;

import java.util.Date;

@Data
public class LoginDto {
    private String username;
    private String token;
    private String fullName;
    private long id;
    private Date expired;
    private Integer kind;

}
