package com.landingis.api.mapper;

import com.landingis.api.dto.employee.EmployeeDto;
import com.landingis.api.form.employee.CreateEmployeeForm;
import com.landingis.api.form.employee.UpdateEmployeeAdminForm;
import com.landingis.api.storage.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    @Mapping(source = "employeeEmail", target = "account.email")
    @Mapping(source = "employeeFullName", target = "account.fullName")
    @Mapping(source = "employeeUsername", target = "account.username")
//    @Mapping(source = "password", target = "account.password")
    @Mapping(source = "employeePhone", target = "account.phone")
    @Mapping(source = "employeeAvatarPath", target = "account.avatarPath")
    @Mapping(source = "employeeAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    Employee fromCreateEmployeeFormToEntity(CreateEmployeeForm createEmployeeForm);

    @Mapping(source = "employeeEmail", target = "account.email")
    @Mapping(source = "employeeFullName", target = "account.fullName")
//    @Mapping(source = "password", target = "account.password")
//    @Mapping(source = "employeePhone", target = "account.phone")
    @Mapping(source = "employeeAvatarPath", target = "account.avatarPath")
    @Mapping(source = "employeeAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateEmployeeAdminFormToEntity(UpdateEmployeeAdminForm updateEmployeeAdminForm, @MappingTarget Employee employee);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.email", target = "employeeEmail")
    @Mapping(source = "account.fullName", target = "employeeFullName")
//    @Mapping(source = "account.password", target = "password")
    @Mapping(source = "account.phone", target = "employeePhone")
    @Mapping(source = "account.avatarPath", target = "employeeAvatarPath")
    @Mapping(source = "address", target = "employeeAddress")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminMapping")
    EmployeeDto fromEntityToEmployeeDto(Employee employee);

    @IterableMapping(elementTargetType = EmployeeDto.class, qualifiedByName = "adminMapping")
    List<EmployeeDto> fromEntityListToAdminDtoList(List<Employee> content);
}
