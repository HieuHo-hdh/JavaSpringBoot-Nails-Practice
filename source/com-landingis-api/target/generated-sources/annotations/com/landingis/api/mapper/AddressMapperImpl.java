package com.landingis.api.mapper;

import com.landingis.api.dto.address.AddressDto;
import com.landingis.api.dto.province.ProvinceDto;
import com.landingis.api.form.address.CreateAddressForm;
import com.landingis.api.form.address.UpdateAddressForm;
import com.landingis.api.storage.model.Address;
import com.landingis.api.storage.model.Customer;
import com.landingis.api.storage.model.Province;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-17T18:30:15+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address fromCreateAddressFormToEntity(CreateAddressForm createAddressForm) {
        if ( createAddressForm == null ) {
            return null;
        }

        Address address = new Address();

        address.setProvince( createAddressFormToProvince( createAddressForm ) );
        address.setDistrict( createAddressFormToProvince1( createAddressForm ) );
        address.setCustomer( createAddressFormToCustomer( createAddressForm ) );
        address.setCommune( createAddressFormToProvince2( createAddressForm ) );
        address.setAddress( createAddressForm.getAddress() );
        address.setPhone( createAddressForm.getPhone() );
        address.setName( createAddressForm.getName() );

        return address;
    }

    @Override
    public void fromUpdateAddressFormToEntity(UpdateAddressForm updateAddressForm, Address address) {
        if ( updateAddressForm == null ) {
            return;
        }

        if ( address.getProvince() == null ) {
            address.setProvince( new Province() );
        }
        updateAddressFormToProvince( updateAddressForm, address.getProvince() );
        if ( address.getDistrict() == null ) {
            address.setDistrict( new Province() );
        }
        updateAddressFormToProvince1( updateAddressForm, address.getDistrict() );
        if ( address.getCommune() == null ) {
            address.setCommune( new Province() );
        }
        updateAddressFormToProvince2( updateAddressForm, address.getCommune() );
        if ( updateAddressForm.getAddress() != null ) {
            address.setAddress( updateAddressForm.getAddress() );
        }
        if ( updateAddressForm.getPhone() != null ) {
            address.setPhone( updateAddressForm.getPhone() );
        }
        if ( updateAddressForm.getName() != null ) {
            address.setName( updateAddressForm.getName() );
        }
        if ( updateAddressForm.getId() != null ) {
            address.setId( updateAddressForm.getId() );
        }
    }

    @Override
    public AddressDto fromEntityToAddressDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        addressDto.setCommuneDto( provinceToProvinceDto( address.getCommune() ) );
        addressDto.setAddress( address.getAddress() );
        addressDto.setDistrictDto( provinceToProvinceDto( address.getDistrict() ) );
        addressDto.setProvinceDto( provinceToProvinceDto( address.getProvince() ) );
        addressDto.setCreatedDate( address.getCreatedDate() );
        addressDto.setPhone( address.getPhone() );
        addressDto.setCreatedBy( address.getCreatedBy() );
        addressDto.setCustomerId( addressCustomerId( address ) );
        addressDto.setName( address.getName() );
        addressDto.setModifiedDate( address.getModifiedDate() );
        addressDto.setModifiedBy( address.getModifiedBy() );
        addressDto.setId( address.getId() );

        return addressDto;
    }

    @Override
    public List<AddressDto> fromEntityListToAddressDtoList(List<Address> content) {
        if ( content == null ) {
            return null;
        }

        List<AddressDto> list = new ArrayList<AddressDto>( content.size() );
        for ( Address address : content ) {
            list.add( fromEntityToAddressDto( address ) );
        }

        return list;
    }

    protected Province createAddressFormToProvince(CreateAddressForm createAddressForm) {
        if ( createAddressForm == null ) {
            return null;
        }

        Province province = new Province();

        province.setId( createAddressForm.getProvinceId() );

        return province;
    }

    protected Province createAddressFormToProvince1(CreateAddressForm createAddressForm) {
        if ( createAddressForm == null ) {
            return null;
        }

        Province province = new Province();

        province.setId( createAddressForm.getDistrictId() );

        return province;
    }

    protected Customer createAddressFormToCustomer(CreateAddressForm createAddressForm) {
        if ( createAddressForm == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( createAddressForm.getCustomerId() );

        return customer;
    }

    protected Province createAddressFormToProvince2(CreateAddressForm createAddressForm) {
        if ( createAddressForm == null ) {
            return null;
        }

        Province province = new Province();

        province.setId( createAddressForm.getCommuneId() );

        return province;
    }

    protected void updateAddressFormToProvince(UpdateAddressForm updateAddressForm, Province mappingTarget) {
        if ( updateAddressForm == null ) {
            return;
        }

        if ( updateAddressForm.getProvinceId() != null ) {
            mappingTarget.setId( updateAddressForm.getProvinceId() );
        }
    }

    protected void updateAddressFormToProvince1(UpdateAddressForm updateAddressForm, Province mappingTarget) {
        if ( updateAddressForm == null ) {
            return;
        }

        if ( updateAddressForm.getDistrictId() != null ) {
            mappingTarget.setId( updateAddressForm.getDistrictId() );
        }
    }

    protected void updateAddressFormToProvince2(UpdateAddressForm updateAddressForm, Province mappingTarget) {
        if ( updateAddressForm == null ) {
            return;
        }

        if ( updateAddressForm.getCommuneId() != null ) {
            mappingTarget.setId( updateAddressForm.getCommuneId() );
        }
    }

    protected ProvinceDto provinceToProvinceDto(Province province) {
        if ( province == null ) {
            return null;
        }

        ProvinceDto provinceDto = new ProvinceDto();

        provinceDto.setStatus( province.getStatus() );
        provinceDto.setModifiedDate( province.getModifiedDate() );
        provinceDto.setCreatedDate( province.getCreatedDate() );
        provinceDto.setModifiedBy( province.getModifiedBy() );
        provinceDto.setCreatedBy( province.getCreatedBy() );
        provinceDto.setId( province.getId() );
        provinceDto.setProvinceName( province.getProvinceName() );
        if ( province.getKind() != null ) {
            provinceDto.setKind( String.valueOf( province.getKind() ) );
        }

        return provinceDto;
    }

    private Long addressCustomerId(Address address) {
        if ( address == null ) {
            return null;
        }
        Customer customer = address.getCustomer();
        if ( customer == null ) {
            return null;
        }
        Long id = customer.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
