package com.landingis.api.dto.address;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.province.ProvinceDto;
import lombok.Data;

@Data
public class AddressDto extends ABasicAdminDto {
    private Long customerId;
    private String name;
    private String phone;
    private String address;

    private ProvinceDto provinceDto;
    private ProvinceDto districtDto;
    private ProvinceDto communeDto;
}
