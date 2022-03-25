package com.landingis.api.mapper;

import com.landingis.api.dto.salaryPeriod.SalaryPeriodDto;
import com.landingis.api.form.salaryPeriod.CreateSalaryPeriodForm;
import com.landingis.api.form.salaryPeriod.UpdateSalaryPeriodForm;
import com.landingis.api.storage.model.SalaryPeriod;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SalaryPeriodMapper {
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("salaryPeriodCreateMapping")
    SalaryPeriod fromCreateSalaryPeriodFormToEntity(CreateSalaryPeriodForm createSalaryPeriodForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @BeanMapping(ignoreByDefault = true)
    @Named("salaryPeriodUpdateMapping")
    SalaryPeriod fromUpdateSalaryPeriodFormToEntity(UpdateSalaryPeriodForm updateSalaryPeriodForm, @MappingTarget SalaryPeriod salaryPeriod);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "startDate", target = "startDate")
    @Mapping(source = "endDate", target = "endDate")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("salaryPeriodMapping")
    SalaryPeriodDto fromEntityToSalaryPeriodDto(SalaryPeriod salaryPeriod);

    @IterableMapping(elementTargetType = SalaryPeriodDto.class, qualifiedByName = "salaryPeriodMapping")
    List<SalaryPeriodDto> fromEntityListToSalaryPeriodDtoList(List<SalaryPeriod> content);
}
