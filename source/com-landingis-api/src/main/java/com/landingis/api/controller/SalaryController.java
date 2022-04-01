package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.salary.SalaryDto;
import com.landingis.api.exception.RequestException;
//import com.landingis.api.form.salary.CreateSalaryForm;
//import com.landingis.api.form.salary.UpdateSalaryForm;
import com.landingis.api.mapper.SalaryMapper;
import com.landingis.api.storage.criteria.SalaryCriteria;
import com.landingis.api.storage.model.Salary;
import com.landingis.api.storage.repository.SalaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/v1/salary")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SalaryController extends ABasicController {

    @Autowired
    SalaryMapper salaryMapper;

    @Autowired
    SalaryRepository salaryRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<SalaryDto>> getList(SalaryCriteria salaryCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.SALARY_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<SalaryDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Salary> listSalary = salaryRepository.findAll(salaryCriteria.getSpecification(), pageable);

        ResponseListObj<SalaryDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(salaryMapper.fromEntityListToSalaryDtoList(listSalary.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listSalary.getTotalPages());
        responseListObj.setTotalElements(listSalary.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List salary success");
        return responseListObjApiMessageDto;
    }

//    @PostMapping(value = "/generate-salary", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<String> create(@Valid @RequestBody CreateSalaryForm createSalaryForm, BindingResult bindingResult) {
//
//        if (!isAdmin()) {
//            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow to create");
//        }
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//        Salary salary = salaryMapper.fromCreateSalaryFormToEntity(createSalaryForm);
//        Date startDate = salary.getStartDate();
//        Date endDate = salary.getEndDate();
//        if (startDate.after(endDate))
//        {
//            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid start date or end date");
//        }
//        else {
//            Page<Salary> listSalary = new PageImpl<>(salaryRepository.findAll());
//            listSalary.forEach(salaryChild -> {
//                if (startDate.before(salaryChild.getStartDate()))
//                {
//                    if (endDate.after(salaryChild.getStartDate()) || endDate.equals(salaryChild.getStartDate()))
//                    {
//                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//                    }
//                }
//                else if (startDate.after(salaryChild.getStartDate()))
//                {
//                    if (startDate.before(salaryChild.getEndDate()) || startDate.equals(salaryChild.getEndDate()))
//                    {
//                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//                    }
//                }
//                else throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//            });
//        }
//        salaryRepository.save(salary);
//        apiMessageDto.setMessage("Create province success");
//        return apiMessageDto;
//    }
//
//    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<String> update(@Valid @RequestBody UpdateSalaryForm updateSalaryForm, BindingResult bindingResult) {
//        if (!isAdmin()) {
//            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow to create");
//        }
//        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
//        Salary salary = salaryRepository.findById(updateSalaryForm.getId()).orElse(null);
//        if (salary == null)
//        {
//            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_NOT_FOUND, "Not found salary period");
//        }
//        Date startDate = updateSalaryForm.getStartDate();
//        Date endDate = updateSalaryForm.getEndDate();
//        if (startDate.after(endDate))
//        {
//            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid start date or end date");
//        }
//        else {
//            Page<Salary> listSalary = new PageImpl<>(salaryRepository.findAll());
//            listSalary.forEach(salaryChild -> {
//                if (!salaryChild.getId().equals(updateSalaryForm.getId())) {
//                    if (startDate.before(salaryChild.getStartDate())) {
//                        if (endDate.after(salaryChild.getStartDate()) || endDate.equals(salaryChild.getStartDate())) {
//                            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//                        }
//                    } else if (startDate.after(salaryChild.getStartDate())) {
//                        if (startDate.before(salaryChild.getEndDate()) || startDate.equals(salaryChild.getEndDate())) {
//                            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//                        }
//                    } else {
//                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
//                    }
//                }
//            });
//        }
//        salaryMapper.fromUpdateSalaryFormToEntity(updateSalaryForm, salary);
//        salaryRepository.save(salary);
//        apiMessageDto.setMessage("Update salary success");
//        return apiMessageDto;
//    }


}
