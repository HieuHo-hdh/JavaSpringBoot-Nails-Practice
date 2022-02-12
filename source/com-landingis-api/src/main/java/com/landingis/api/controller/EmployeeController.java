package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.employee.EmployeeDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.employee.CreateEmployeeForm;
import com.landingis.api.form.employee.UpdateEmployeeAdminForm;
import com.landingis.api.mapper.EmployeeMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.EmployeeCriteria;
import com.landingis.api.storage.model.Employee;
import com.landingis.api.storage.model.Group;
import com.landingis.api.storage.repository.AccountRepository;
import com.landingis.api.storage.repository.EmployeeRepository;
import com.landingis.api.storage.repository.GroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/v1/employee")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class EmployeeController extends ABasicController {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<EmployeeDto>> getList(EmployeeCriteria employeeCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<EmployeeDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Employee> listEmployee = employeeRepository.findAll(employeeCriteria.getSpecification(), pageable);

        ResponseListObj<EmployeeDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(employeeMapper.fromEntityListToAdminDtoList(listEmployee.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listEmployee.getTotalPages());
        responseListObj.setTotalElements(listEmployee.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List employee success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<EmployeeDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_UNAUTHORIZED, "Not allow get employee");
        }
        ApiMessageDto<EmployeeDto> result = new ApiMessageDto<>();
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null)
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_NOT_FOUND, "Not found employee");
        }
        result.setData(employeeMapper.fromEntityToEmployeeDto(employee));
        result.setMessage("Set Employee Sucess");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateEmployeeForm createEmployeeForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Long accountCheck = accountRepository.countAccountByPhoneOrUsername(createEmployeeForm.getEmployeePhone(), createEmployeeForm.getEmployeeUsername());
        if (accountCheck > 0)
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_PHONE_EXIST, "Phone exists.");
        }
        Group group = groupRepository.findFirstByKind(LandingISConstant.GROUP_KIND_EMPLOYEE);

        if(group == null)
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_GROUP_NOT_EXIST, "Group does not exist");
        }

        Employee employee = employeeMapper.fromCreateEmployeeFormToEntity(createEmployeeForm);

        employee.getAccount().setGroup(group);
        employee.getAccount().setKind(LandingISConstant.GROUP_KIND_EMPLOYEE);
        employee.getAccount().setPassword(passwordEncoder.encode(createEmployeeForm.getPassword()));
        employee.getAccount().setStatus(createEmployeeForm.getStatus());
        employee.setIsAdminCreated(true);
        
        employeeRepository.save(employee);
        apiMessageDto.setMessage("Create employee success");
        return apiMessageDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateEmployeeAdminForm updateEmployeeAdminForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Employee employee = employeeRepository.findById(updateEmployeeAdminForm.getId()).orElse(null);

        if(employee == null)
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_NOT_FOUND, "Not found employee");
        }
        

        employeeMapper.fromUpdateEmployeeAdminFormToEntity(updateEmployeeAdminForm, employee);
        
        if(StringUtils.isNoneBlank(updateEmployeeAdminForm.getPassword()))
        {
            employee.getAccount().setPassword(passwordEncoder.encode(updateEmployeeAdminForm.getPassword()));
        }

        if(StringUtils.isNoneBlank(updateEmployeeAdminForm.getEmployeeAvatarPath()))
        {
            if(!updateEmployeeAdminForm.getEmployeeAvatarPath().equals(employee.getAccount().getAvatarPath()))
            {
                landingIsApiService.deleteFile(employee.getAccount().getAvatarPath());
            }
            employee.getAccount().setAvatarPath(updateEmployeeAdminForm.getEmployeeAvatarPath());
        }
        employee.getAccount().setStatus(updateEmployeeAdminForm.getStatus());
        accountRepository.save(employee.getAccount());
        employeeRepository.save(employee);
        apiMessageDto.setMessage("Update employee success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/id")
    public ApiMessageDto<EmployeeDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<EmployeeDto> result = new ApiMessageDto<>();
        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null)
        {
            throw new RequestException(ErrorCode.EMPLOYEE_ERROR_NOT_FOUND, "Not found employee");
        }

        landingIsApiService.deleteFile(employee.getAccount().getAvatarPath());

        employeeRepository.delete(employee);
        result.setMessage("Delete Employee Sucess");
        return result;
    }
}
