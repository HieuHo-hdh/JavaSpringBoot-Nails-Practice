package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.salaryPeriod.SalaryPeriodDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.salaryPeriod.CreateSalaryPeriodForm;
import com.landingis.api.form.salaryPeriod.UpdateSalaryPeriodForm;
import com.landingis.api.mapper.SalaryPeriodMapper;
import com.landingis.api.storage.criteria.SalaryPeriodCriteria;
import com.landingis.api.storage.model.SalaryPeriod;
import com.landingis.api.storage.repository.SalaryPeriodRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/v1/salary-period")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SalaryPeriodController extends ABasicController {

    @Autowired
    SalaryPeriodMapper salaryPeriodMapper;

    @Autowired
    SalaryPeriodRepository salaryPeriodRepository;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<SalaryPeriodDto>> getList(SalaryPeriodCriteria salaryPeriodCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<SalaryPeriodDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<SalaryPeriod> listSalaryPeriod = salaryPeriodRepository.findAll(salaryPeriodCriteria.getSpecification(), pageable);

        ResponseListObj<SalaryPeriodDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(salaryPeriodMapper.fromEntityListToSalaryPeriodDtoList(listSalaryPeriod.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listSalaryPeriod.getTotalPages());
        responseListObj.setTotalElements(listSalaryPeriod.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List salary period success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<SalaryPeriodDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow get salary period");
        }
        ApiMessageDto<SalaryPeriodDto> result = new ApiMessageDto<>();
        SalaryPeriod salaryPeriod = salaryPeriodRepository.findById(id).orElse(null);

        if (salaryPeriod == null)
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_NOT_FOUND, "Not found salary period");
        }
        result.setData(salaryPeriodMapper.fromEntityToSalaryPeriodDto(salaryPeriod));
        result.setMessage("Set Province Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateSalaryPeriodForm createSalaryPeriodForm, BindingResult bindingResult) {

        if (!isAdmin()) {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SalaryPeriod salaryPeriod = salaryPeriodMapper.fromCreateSalaryPeriodFormToEntity(createSalaryPeriodForm);
        Date startDate = salaryPeriod.getStartDate();
        Date endDate = salaryPeriod.getEndDate();
        if (startDate.after(endDate))
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid start date or end date");
        }
        else {
            Page<SalaryPeriod> listSalaryPeriod = new PageImpl<>(salaryPeriodRepository.findAll());
            listSalaryPeriod.forEach(salaryPeriodChild -> {
                if (startDate.before(salaryPeriodChild.getStartDate()))
                {
                    if (endDate.after(salaryPeriodChild.getStartDate()) || endDate.equals(salaryPeriodChild.getStartDate()))
                    {
                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
                    }
                }
                else if (startDate.after(salaryPeriodChild.getStartDate()))
                {
                    if (startDate.before(salaryPeriodChild.getEndDate()) || startDate.equals(salaryPeriodChild.getEndDate()))
                    {
                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
                    }
                }
                else throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
            });
        }
        salaryPeriodRepository.save(salaryPeriod);
        apiMessageDto.setMessage("Create province success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateSalaryPeriodForm updateSalaryPeriodForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        SalaryPeriod salaryPeriod = salaryPeriodRepository.findById(updateSalaryPeriodForm.getId()).orElse(null);
        if (salaryPeriod == null)
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_NOT_FOUND, "Not found salary period");
        }
        Date startDate = updateSalaryPeriodForm.getStartDate();
        Date endDate = updateSalaryPeriodForm.getEndDate();
        if (startDate.after(endDate))
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid start date or end date");
        }
        else {
            Page<SalaryPeriod> listSalaryPeriod = new PageImpl<>(salaryPeriodRepository.findAll());
            listSalaryPeriod.forEach(salaryPeriodChild -> {
                if (!salaryPeriodChild.getId().equals(updateSalaryPeriodForm.getId())) {
                    if (startDate.before(salaryPeriodChild.getStartDate())) {
                        if (endDate.after(salaryPeriodChild.getStartDate()) || endDate.equals(salaryPeriodChild.getStartDate())) {
                            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
                        }
                    } else if (startDate.after(salaryPeriodChild.getStartDate())) {
                        if (startDate.before(salaryPeriodChild.getEndDate()) || startDate.equals(salaryPeriodChild.getEndDate())) {
                            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
                        }
                    } else {
                        throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_INVALID_TIME, "Invalid time duration");
                    }
                }
            });
        }
        salaryPeriodMapper.fromUpdateSalaryPeriodFormToEntity(updateSalaryPeriodForm, salaryPeriod);
        salaryPeriodRepository.save(salaryPeriod);
        apiMessageDto.setMessage("Update salary period success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<String> result = new ApiMessageDto<>();
        SalaryPeriod salaryPeriod = salaryPeriodRepository.findById(id).orElse(null);

        if (salaryPeriod == null)
        {
            throw new RequestException(ErrorCode.SALARY_PERIOD_ERROR_NOT_FOUND, "Not found salary period");
        }

        salaryPeriodRepository.delete(salaryPeriod);
        result.setMessage("Delete salary period Sucess");
        return result;
    }

}
