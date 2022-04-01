package com.landingis.api.mapper;

import com.landingis.api.dto.salary.SalaryDto;
//import com.landingis.api.form.salary.CreateSalaryForm;
//import com.landingis.api.form.salary.UpdateSalaryForm;
import com.landingis.api.storage.model.Salary;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SalaryMapper {
//    @Mapping(source = "startDate", target = "startDate")
//    @Mapping(source = "endDate", target = "endDate")
//    @BeanMapping(ignoreByDefault = true)
//    @Named("SalaryCreateMapping")
//    Salary fromCreateSalaryFormToEntity(CreateSalaryForm createSalaryForm);
//
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "startDate", target = "startDate")
//    @Mapping(source = "endDate", target = "endDate")
//    @BeanMapping(ignoreByDefault = true)
//    @Named("SalaryUpdateMapping")
//    Salary fromUpdateSalaryFormToEntity(UpdateSalaryForm updateSalaryForm, @MappingTarget Salary Salary);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "isPayment", target = "isPayment")
    @Mapping(source = "commission", target = "commission")

    @Mapping(source = "revenue", target = "revenue")
    @Mapping(source = "salaryPeriod.id", target = "salaryPeriodId")
    @Mapping(source = "salaryPrice", target = "salaryPrice")
    @Mapping(source = "payDate", target = "payDate")

    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("SalaryMapping")
    SalaryDto fromEntityToSalaryDto(Salary Salary);

    @IterableMapping(elementTargetType = SalaryDto.class, qualifiedByName = "SalaryMapping")
    List<SalaryDto> fromEntityListToSalaryDtoList(List<Salary> content);
}
