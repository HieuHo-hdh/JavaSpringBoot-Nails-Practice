package com.landingis.api.mapper;

import com.landingis.api.dto.province.ProvinceDto;
import com.landingis.api.form.province.UpdateProvinceForm;
import com.landingis.api.form.province.CreateProvinceForm;
import com.landingis.api.storage.model.Province;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProvinceMapper {

    @Mapping(source = "provinceName", target = "provinceName")
    @BeanMapping(ignoreByDefault = true)
    @Named("provinceCreateMapping")
    Province fromCreateProvinceFormToEntity(CreateProvinceForm createProvinceForm);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "provinceName", target = "provinceName")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("provinceUpdateMapping")
    void fromUpdateProvinceFormToEntity(UpdateProvinceForm updateProvinceForm, @MappingTarget Province province);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "provinceName", target = "provinceName")
    @Mapping(source = "parentProvince.id", target = "parentId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("provinceMapping")
    ProvinceDto fromEntityToProvinceDto(Province province);

    @IterableMapping(elementTargetType = ProvinceDto.class, qualifiedByName = "provinceMapping")
    List<ProvinceDto> fromEntityListToProvinceDtoList(List<Province> content);
}
