package com.landingis.api.mapper;

import com.landingis.api.dto.address.AddressDto;
import com.landingis.api.form.address.CreateAddressForm;
import com.landingis.api.form.address.UpdateAddressForm;
import com.landingis.api.storage.model.Address;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AddressMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @BeanMapping(ignoreByDefault = true)
    @Named("addressCreateMapping")
    Address fromCreateAddressFormToEntity(CreateAddressForm createAddressForm);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "districtId", target = "district.id")
    @Mapping(source = "communeId", target = "commune.id")
    @BeanMapping(ignoreByDefault = true)
    @Named("addressUpdateMapping")
    void fromUpdateAddressFormToEntity(UpdateAddressForm updateAddressForm, @MappingTarget Address address);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "phone", target = "phone")
    @Mapping(source = "province", target = "provinceDto")
    @Mapping(source = "district", target = "districtDto")
    @Mapping(source = "commune", target = "communeDto")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("addressMapping")
    AddressDto fromEntityToAddressDto(Address address);

    @IterableMapping(elementTargetType = AddressDto.class, qualifiedByName = "addressMapping")
    List<AddressDto> fromEntityListToAddressDtoList(List<Address> content);
}
