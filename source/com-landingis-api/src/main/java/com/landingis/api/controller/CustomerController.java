package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.customer.CustomerDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.customer.CreateCustomerForm;
import com.landingis.api.form.customer.UpdateCustomerAdminForm;
import com.landingis.api.mapper.CustomerMapper;
import com.landingis.api.storage.criteria.CustomerCriteria;
import com.landingis.api.storage.model.Customer;
import com.landingis.api.storage.model.Group;
import com.landingis.api.storage.repository.AccountRepository;
import com.landingis.api.storage.repository.CustomerRepository;
import com.landingis.api.storage.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.landingis.api.service.LandingIsApiService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/v1/customer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CustomerController extends ABasicController{
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CustomerDto>> getList(CustomerCriteria customerCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<CustomerDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Customer> listCustomer = customerRepository.findAll(customerCriteria.getSpecification(), pageable);

        ResponseListObj<CustomerDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(customerMapper.fromEntityListToAdminDtoList(listCustomer.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listCustomer.getTotalPages());
        responseListObj.setTotalElements(listCustomer.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List customer success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CustomerDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_UNAUTHORIZED, "Not allow get customer");
        }
        ApiMessageDto<CustomerDto> result = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null)
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_NOT_FOUND, "Not found customer");
        }
        result.setData(customerMapper.fromEntityToCustomerDto(customer));
        result.setMessage("Set Customer Sucess");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCustomerForm createCustomerForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Long accountCheck = accountRepository.countAccountByPhone(createCustomerForm.getCustomerPhone());
        if (accountCheck > 0)
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_PHONE_EXIST, "Phone exists.");
        }
        Group group = groupRepository.findFirstByKind(LandingISConstant.GROUP_KIND_CUSTOMER);

        if(group == null)
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_GROUP_NOT_EXIST, "Group does not exist");
        }

        Customer customer = customerMapper.fromCreateCustomerFormToEntity(createCustomerForm);

        customer.getAccount().setGroup(group);
        customer.getAccount().setKind(LandingISConstant.GROUP_KIND_CUSTOMER);
        customer.getAccount().setPassword(passwordEncoder.encode(createCustomerForm.getCustomerPassword()));
        customer.getAccount().setStatus(createCustomerForm.getStatus());
        customer.setIsAdminCreated(true);

        if(Boolean.TRUE == createCustomerForm.getIsLoyalty())
        {
            customer.setLoyaltyDate(new Date());
        }

        customerRepository.save(customer);
        apiMessageDto.setMessage("Create customer success");
        return apiMessageDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCustomerAdminForm updateCustomerAdminForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(updateCustomerAdminForm.getId()).orElse(null);

        if(customer == null)
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_NOT_FOUND, "Not found customer");
        }

        if(customer.getIsLoyalty().equals(Boolean.FALSE)
                && updateCustomerAdminForm.getIsLoyalty().equals(Boolean.TRUE))
        {
            customer.setLoyaltyDate(new Date());
        }

        customerMapper.fromUpdateCustomerAdminFormToEntity(updateCustomerAdminForm, customer);

        if (updateCustomerAdminForm.getIsLoyalty().equals(Boolean.FALSE))
        {
            customer.setLoyaltyDate(null);
            customer.setLoyaltyLevel(null);
            customer.setSaleOff(0);
        }

        if(StringUtils.isNoneBlank(updateCustomerAdminForm.getCustomerPassword()))
        {
            customer.getAccount().setPassword(passwordEncoder.encode(updateCustomerAdminForm.getCustomerPassword()));
        }

        if(StringUtils.isNoneBlank(updateCustomerAdminForm.getCustomerAvatarPath()))
        {
            if(!updateCustomerAdminForm.getCustomerAvatarPath().equals(customer.getAccount().getAvatarPath()))
            {
                landingIsApiService.deleteFile(customer.getAccount().getAvatarPath());
            }
            customer.getAccount().setAvatarPath(updateCustomerAdminForm.getCustomerAvatarPath());
        }
        customer.getAccount().setStatus(updateCustomerAdminForm.getStatus());
        accountRepository.save(customer.getAccount());
        customerRepository.save(customer);
        apiMessageDto.setMessage("Update customer success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<CustomerDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<CustomerDto> result = new ApiMessageDto<>();
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer == null)
        {
            throw new RequestException(ErrorCode.CUSTOMER_ERROR_NOT_FOUND, "Not found customer");
        }

        landingIsApiService.deleteFile(customer.getAccount().getAvatarPath());

        customerRepository.delete(customer);
        result.setMessage("Delete Customer Sucess");
        return result;
    }
}
