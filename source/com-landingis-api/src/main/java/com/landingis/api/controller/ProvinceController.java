package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.province.ProvinceDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.province.CreateProvinceForm;
import com.landingis.api.form.province.UpdateProvinceForm;
import com.landingis.api.mapper.ProvinceMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.ProvinceCriteria;
import com.landingis.api.storage.model.Province;
import com.landingis.api.storage.repository.ProvinceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/province")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ProvinceController extends ABasicController {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    ProvinceMapper provinceMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ProvinceDto>> getList(ProvinceCriteria provinceCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<ProvinceDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Province> listProvince = provinceRepository.findAll(provinceCriteria.getSpecification(), pageable);

        ResponseListObj<ProvinceDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(provinceMapper.fromEntityListToProvinceDtoList(listProvince.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listProvince.getTotalPages());
        responseListObj.setTotalElements(listProvince.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List province success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ProvinceDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_UNAUTHORIZED, "Not allow get province");
        }
        ApiMessageDto<ProvinceDto> result = new ApiMessageDto<>();
        Province province = provinceRepository.findById(id).orElse(null);

        if (province == null)
        {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_NOT_FOUND, "Not found province");
        }
        result.setData(provinceMapper.fromEntityToProvinceDto(province));
        result.setMessage("Set Province Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateProvinceForm createProvinceForm, BindingResult bindingResult) {

        if(!isAdmin()){
            throw new RequestException(ErrorCode.PROVINCE_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        //Default Kind
        Province province = provinceMapper.fromCreateProvinceFormToEntity(createProvinceForm);
        province.setKind(LandingISConstant.PROVINCE_KIND_PROVINCE);

        if(createProvinceForm.getParentId() != null)
        {
            //Take parent Id province if exists
            Province parentProvince = provinceRepository.findById(
                    createProvinceForm.getParentId()
            ).orElse(null);

            if(parentProvince == null)
            {
                throw new RequestException(ErrorCode.PROVINCE_ERROR_NOT_FOUND);
            }

            //Define Province Kind
            if(parentProvince.getKind().equals(LandingISConstant.PROVINCE_KIND_PROVINCE))
            {
                province.setKind(LandingISConstant.PROVINCE_KIND_DISTRICT);
            }
            else if(parentProvince.getKind().equals(LandingISConstant.PROVINCE_KIND_DISTRICT))
            {
                province.setKind(LandingISConstant.PROVINCE_KIND_COMMUNE);
            }

            province.setParentProvince(parentProvince);
        }

        provinceRepository.save(province);
        apiMessageDto.setMessage("Create province success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateProvinceForm updateProvinceAdminForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.PROVINCE_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Province province = provinceRepository.findById(updateProvinceAdminForm.getId()).orElse(null);

        if(province == null)
        {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_NOT_FOUND, "Not found province");
        }

        provinceMapper.fromUpdateProvinceFormToEntity(updateProvinceAdminForm, province);

        provinceRepository.save(province);
        apiMessageDto.setMessage("Update province success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<ProvinceDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<ProvinceDto> result = new ApiMessageDto<>();
        Province province = provinceRepository.findById(id).orElse(null);

        if (province == null)
        {
            throw new RequestException(ErrorCode.PROVINCE_ERROR_NOT_FOUND, "Not found province");
        }

        provinceRepository.delete(province);
        result.setMessage("Delete Province Sucess");
        return result;
    }
}
