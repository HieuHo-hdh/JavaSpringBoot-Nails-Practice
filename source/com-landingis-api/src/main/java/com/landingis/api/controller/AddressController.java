package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.address.AddressDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.address.CreateAddressForm;
import com.landingis.api.form.address.UpdateAddressForm;
import com.landingis.api.mapper.AddressMapper;
import com.landingis.api.storage.criteria.AddressCriteria;
import com.landingis.api.storage.model.Address;

import com.landingis.api.storage.model.Customer;
import com.landingis.api.storage.model.Province;
import com.landingis.api.storage.repository.AddressRepository;
import com.landingis.api.storage.repository.CustomerRepository;
import com.landingis.api.storage.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.landingis.api.constant.LandingISConstant.*;

@RestController
@RequestMapping("/v1/address")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class AddressController extends ABasicController {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    AddressMapper addressMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<AddressDto>> getList(AddressCriteria addressCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<AddressDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Address> listAddress = addressRepository.findAll(addressCriteria.getSpecification(), pageable);

        ResponseListObj<AddressDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(addressMapper.fromEntityListToAddressDtoList(listAddress.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listAddress.getTotalPages());
        responseListObj.setTotalElements(listAddress.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List address success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<AddressDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow get address");
        }
        ApiMessageDto<AddressDto> result = new ApiMessageDto<>();
        Address address = addressRepository.findById(id).orElse(null);

        if (address == null)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND, "Not found address");
        }
        result.setData(addressMapper.fromEntityToAddressDto(address));
        result.setMessage("Set Address Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateAddressForm createAddressForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow to create");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(createAddressForm.getCustomerId()).orElse(null);
        if (customer == null)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_CUSTOMER, "Not found customer");
        }

        Province province = provinceRepository.findById(createAddressForm.getProvinceId()).orElse(null);
        if (province == null || province.getStatus() != STATUS_ACTIVE || province.getKind() != PROVINCE_KIND_PROVINCE)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_PROVINCE, "Not found province");
        }
        Province district = provinceRepository.findById(createAddressForm.getDistrictId()).orElse(null);
        //2nd condition: Check if exists district by comparing district's parentId and provinceId
        if(district == null || !district.getParentProvince().getId().equals(province.getId()) || district.getStatus() != STATUS_ACTIVE || district.getKind() != PROVINCE_KIND_DISTRICT)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_DISTRICT, "Not found district");
        }
        //2nd condition: Check if exists commune by comparing commune's parentId and districtId
        Province commune = provinceRepository.findById(createAddressForm.getCommuneId()).orElse(null);
        if (commune == null || !commune.getParentProvince().getId().equals(district.getId()) || commune.getStatus() != STATUS_ACTIVE || commune.getKind() != PROVINCE_KIND_COMMUNE)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_COMMUNE, "Not found commune");
        }
        Address address = addressMapper.fromCreateAddressFormToEntity(createAddressForm);
        addressRepository.save(address);
        apiMessageDto.setMessage("Create address success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateAddressForm updateAddressForm, BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow to create");
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Address address = addressRepository.findById(updateAddressForm.getId()).orElse(null);
        if (address == null)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND, "Not found address");
        }

        Province province = provinceRepository.findById(updateAddressForm.getProvinceId()).orElse(null);
        if (province == null || province.getStatus() != STATUS_ACTIVE || province.getKind() != PROVINCE_KIND_PROVINCE)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND, "Not found address");
        }

        Province district = provinceRepository.findById(updateAddressForm.getDistrictId()).orElse(null);
        //2nd condition: Check if exists district by comparing district's parentId and provinceId
        if(district == null || !district.getParentProvince().getId().equals(province.getId()) || district.getStatus() != STATUS_ACTIVE || district.getKind() != PROVINCE_KIND_DISTRICT)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_DISTRICT, "Not found district");
        }
        //2nd condition: Check if exists commune by comparing commune's parentId and districtId
        Province commune = provinceRepository.findById(updateAddressForm.getCommuneId()).orElse(null);
        if (commune == null || !commune.getParentProvince().getId().equals(district.getId()) || commune.getStatus() != STATUS_ACTIVE || commune.getKind() != PROVINCE_KIND_COMMUNE)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND_COMMUNE, "Not found commune");
        }

        addressMapper.fromUpdateAddressFormToEntity(updateAddressForm, address);

        addressRepository.save(address);
        apiMessageDto.setMessage("Update address success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<String> result = new ApiMessageDto<>();
        Address address = addressRepository.findById(id).orElse(null);

        if (address == null)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND, "Not found address");
        }

        addressRepository.delete(address);
        result.setMessage("Delete Address Sucess");
        return result;
    }

}
