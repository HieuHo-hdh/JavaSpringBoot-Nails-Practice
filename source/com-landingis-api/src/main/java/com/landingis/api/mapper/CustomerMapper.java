package com.landingis.api.mapper;

import com.landingis.api.dto.customer.CustomerDto;
import com.landingis.api.form.customer.CreateCustomerForm;
import com.landingis.api.form.customer.UpdateCustomerAdminForm;
import com.landingis.api.storage.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface CustomerMapper {
    @Mapping(source = "customerEmail", target = "account.email")
    @Mapping(source = "customerFullName", target = "account.fullName")
    @Mapping(source = "customerPhone", target = "account.phone")
    @Mapping(source = "customerAvatarPath", target = "account.avatarPath")
    @Mapping(source = "customerAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "isLoyalty", target = "isLoyalty")
    @Mapping(source = "loyaltyLevel", target = "loyaltyLevel")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    Customer fromCreateCustomerFormToEntity(CreateCustomerForm createCustomerForm);

    @Mapping(source = "customerEmail", target = "account.email")
    @Mapping(source = "customerFullName", target = "account.fullName")
    @Mapping(source = "customerAvatarPath", target = "account.avatarPath")
    @Mapping(source = "customerAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "isLoyalty", target = "isLoyalty")
    @Mapping(source = "loyaltyLevel", target = "loyaltyLevel")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateCustomerAdminFormToEntity(UpdateCustomerAdminForm updateCustomerAdminForm, @MappingTarget Customer customer);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.email", target = "customerEmail")
    @Mapping(source = "account.fullName", target = "customerFullName")
    @Mapping(source = "account.phone", target = "customerPhone")
    @Mapping(source = "account.avatarPath", target = "customerAvatarPath")
    @Mapping(source = "address", target = "customerAddress")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "isLoyalty", target = "isLoyalty")
    @Mapping(source = "loyaltyLevel", target = "loyaltyLevel")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminMapping")
    CustomerDto fromEntityToCustomerDto(Customer customer);

    @IterableMapping(elementTargetType = CustomerDto.class, qualifiedByName = "adminMapping")
    List<CustomerDto> fromEntityListToAdminDtoList(List<Customer> content);
}
