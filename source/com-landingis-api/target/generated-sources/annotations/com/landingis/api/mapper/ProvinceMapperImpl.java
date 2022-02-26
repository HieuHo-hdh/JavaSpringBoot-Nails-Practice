package com.landingis.api.mapper;

import com.landingis.api.dto.province.ProvinceDto;
import com.landingis.api.form.province.CreateProvinceForm;
import com.landingis.api.form.province.UpdateProvinceForm;
import com.landingis.api.storage.model.Province;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-26T12:30:30+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ProvinceMapperImpl implements ProvinceMapper {

    @Override
    public Province fromCreateProvinceFormToEntity(CreateProvinceForm createProvinceForm) {
        if ( createProvinceForm == null ) {
            return null;
        }

        Province province = new Province();

        province.setProvinceName( createProvinceForm.getProvinceName() );

        return province;
    }

    @Override
    public void fromUpdateProvinceFormToEntity(UpdateProvinceForm updateProvinceForm, Province province) {
        if ( updateProvinceForm == null ) {
            return;
        }

        if ( updateProvinceForm.getId() != null ) {
            province.setId( updateProvinceForm.getId() );
        }
        if ( updateProvinceForm.getProvinceName() != null ) {
            province.setProvinceName( updateProvinceForm.getProvinceName() );
        }
        if ( updateProvinceForm.getStatus() != null ) {
            province.setStatus( updateProvinceForm.getStatus() );
        }
    }

    @Override
    public ProvinceDto fromEntityToProvinceDto(Province province) {
        if ( province == null ) {
            return null;
        }

        ProvinceDto provinceDto = new ProvinceDto();

        provinceDto.setCreatedDate( province.getCreatedDate() );
        provinceDto.setCreatedBy( province.getCreatedBy() );
        if ( province.getKind() != null ) {
            provinceDto.setKind( String.valueOf( province.getKind() ) );
        }
        provinceDto.setModifiedDate( province.getModifiedDate() );
        provinceDto.setModifiedBy( province.getModifiedBy() );
        provinceDto.setId( province.getId() );
        provinceDto.setProvinceName( province.getProvinceName() );
        provinceDto.setParentId( provinceParentProvinceId( province ) );
        provinceDto.setStatus( province.getStatus() );

        return provinceDto;
    }

    @Override
    public List<ProvinceDto> fromEntityListToProvinceDtoList(List<Province> content) {
        if ( content == null ) {
            return null;
        }

        List<ProvinceDto> list = new ArrayList<ProvinceDto>( content.size() );
        for ( Province province : content ) {
            list.add( fromEntityToProvinceDto( province ) );
        }

        return list;
    }

    private Long provinceParentProvinceId(Province province) {
        if ( province == null ) {
            return null;
        }
        Province parentProvince = province.getParentProvince();
        if ( parentProvince == null ) {
            return null;
        }
        Long id = parentProvince.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
