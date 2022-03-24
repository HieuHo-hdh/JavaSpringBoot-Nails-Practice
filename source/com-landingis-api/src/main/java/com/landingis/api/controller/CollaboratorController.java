package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.collaborator.CollaboratorDto;
import com.landingis.api.dto.collaboratorTotalMoneyReport.CollaboratorTotalMoneyReportDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.collaborator.CreateCollaboratorForm;
import com.landingis.api.form.collaborator.UpdateCollaboratorAdminForm;
import com.landingis.api.mapper.CollaboratorMapper;
import com.landingis.api.mapper.CollaboratorTotalMoneyReportMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.CollaboratorCriteria;
import com.landingis.api.storage.criteria.CollaboratorTotalMoneyReportCriteria;
import com.landingis.api.storage.model.Collaborator;
import com.landingis.api.storage.model.Employee;
import com.landingis.api.storage.model.Group;
import com.landingis.api.storage.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/collaborator")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CollaboratorController extends ABasicController {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CollaboratorRepository collaboratorRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CollaboratorTotalMoneyReportRepository collaboratorTotalMoneyReportRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    CollaboratorMapper collaboratorMapper;

    @Autowired
    CollaboratorTotalMoneyReportMapper collaboratorTotalMoneyReportMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CollaboratorDto>> getList(CollaboratorCriteria collaboratorCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<CollaboratorDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Collaborator> listCollaborator = collaboratorRepository.findAll(collaboratorCriteria.getSpecification(), pageable);

        ResponseListObj<CollaboratorDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(collaboratorMapper.fromEntityListToAdminDtoList(listCollaborator.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listCollaborator.getTotalPages());
        responseListObj.setTotalElements(listCollaborator.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List collaborator success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/collaborators-total-money-report", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CollaboratorTotalMoneyReportDto>> getOrdersTotalMoneyReportList(CollaboratorTotalMoneyReportCriteria collaboratorTotalMoneyReportCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ResponseListObj<CollaboratorTotalMoneyReportDto>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Date fromDate = collaboratorTotalMoneyReportCriteria.getFrom();
        Date toDate = collaboratorTotalMoneyReportCriteria.getTo();
        if (fromDate != null && toDate != null)
        {
            if (fromDate.after(toDate))
            {
                throw new RequestException(ErrorCode.COLLABORATOR_TOTAL_MONEY_REPORT_ERROR_UNAUTHORIZED, "Invalid time duration");
            }
        }
        Integer state = LandingISConstant.ORDERS_STATE_DONE;

        Page<Collaborator> listCollaborators = collaboratorTotalMoneyReportRepository.findAll(collaboratorTotalMoneyReportCriteria.getSpecification(), pageable);
        List<CollaboratorTotalMoneyReportDto> collaboratorTotalMoneyReportList = collaboratorTotalMoneyReportMapper.fromEntityListToCollaboratorTotalMoneyReportDtoList(listCollaborators.getContent());

        collaboratorTotalMoneyReportList.forEach(collaborator -> {
            Double sum = 0d;
            if (fromDate != null && toDate!= null)
            {
                sum = collaboratorTotalMoneyReportRepository.getOrdersTotalMoneyFromDateToDate(fromDate, toDate, state, collaborator.getCollaboratorId());
            }
            else if (fromDate != null)
            {
                sum = collaboratorTotalMoneyReportRepository.getOrdersTotalMoneyFromDate(fromDate, state, collaborator.getCollaboratorId());
            }
            else if (toDate!= null)
            {
                sum = collaboratorTotalMoneyReportRepository.getOrdersTotalMoneyToDate(toDate, state, collaborator.getCollaboratorId());
            }
            else
            {
                sum = collaboratorTotalMoneyReportRepository.getOrdersTotalMoney(state, collaborator.getCollaboratorId());
            }
            if (sum == null) sum = 0d;
            collaborator.setTotalMoney(sum);
        });

        ResponseListObj<CollaboratorTotalMoneyReportDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(collaboratorTotalMoneyReportList);
//        responseListObj.setData(collaboratorTotalMoneyReportMapper.fromEntityListToCollaboratorTotalMoneyReportDtoList(listCollaborators.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listCollaborators.getTotalPages());
        responseListObj.setTotalElements(listCollaborators.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List collaborator total money report success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CollaboratorDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allow get collaborator");
        }
        ApiMessageDto<CollaboratorDto> result = new ApiMessageDto<>();
        Collaborator collaborator = collaboratorRepository.findById(id).orElse(null);

        if (collaborator == null)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_NOT_FOUND, "Not found collaborator");
        }
        result.setData(collaboratorMapper.fromEntityToCollaboratorDto(collaborator));
        result.setMessage("Set Collaborator Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCollaboratorForm createCollaboratorForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Employee employee = employeeRepository.findById(createCollaboratorForm.getEmployeeId()).orElse(null);

        if (employee == null)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_NOT_FOUND_EMPLOYEE, "Not found employee");
        }
        Long accountCheck = accountRepository.countAccountByPhoneOrUsername(createCollaboratorForm.getCollaboratorPhone(), createCollaboratorForm.getCollaboratorUsername());
        if (accountCheck > 0)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_PHONE_EXIST, "Phone or username exists.");
        }
        Group group = groupRepository.findFirstByKind(LandingISConstant.GROUP_KIND_COLLABORATOR);

        if(group == null)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_GROUP_NOT_EXIST, "Group does not exist");
        }

        Collaborator collaborator = collaboratorMapper.fromCreateCollaboratorFormToEntity(createCollaboratorForm);

        collaborator.getAccount().setGroup(group);
        collaborator.getAccount().setKind(LandingISConstant.GROUP_KIND_COLLABORATOR);
        collaborator.getAccount().setPassword(passwordEncoder.encode(createCollaboratorForm.getPassword()));
        collaborator.getAccount().setStatus(createCollaboratorForm.getStatus());
        collaborator.setIsAdminCreated(true);

        collaboratorRepository.save(collaborator);
        apiMessageDto.setMessage("Create collaborator success");
        return apiMessageDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCollaboratorAdminForm updateCollaboratorAdminForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Collaborator collaborator = collaboratorRepository.findById(updateCollaboratorAdminForm.getId()).orElse(null);

        if(collaborator == null)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_NOT_FOUND, "Not found collaborator");
        }


        collaboratorMapper.fromUpdateCollaboratorAdminFormToEntity(updateCollaboratorAdminForm, collaborator);

        if(StringUtils.isNoneBlank(updateCollaboratorAdminForm.getPassword()))
        {
            collaborator.getAccount().setPassword(passwordEncoder.encode(updateCollaboratorAdminForm.getPassword()));
        }

        if(StringUtils.isNoneBlank(updateCollaboratorAdminForm.getCollaboratorAvatarPath()))
        {
            if(!updateCollaboratorAdminForm.getCollaboratorAvatarPath().equals(collaborator.getAccount().getAvatarPath()))
            {
                landingIsApiService.deleteFile(collaborator.getAccount().getAvatarPath());
            }
            collaborator.getAccount().setAvatarPath(updateCollaboratorAdminForm.getCollaboratorAvatarPath());
        }
        collaborator.getAccount().setStatus(updateCollaboratorAdminForm.getStatus());
        accountRepository.save(collaborator.getAccount());
        collaboratorRepository.save(collaborator);
        apiMessageDto.setMessage("Update collaborator success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<CollaboratorDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<CollaboratorDto> result = new ApiMessageDto<>();
        Collaborator collaborator = collaboratorRepository.findById(id).orElse(null);

        if (collaborator == null)
        {
            throw new RequestException(ErrorCode.COLLABORATOR_ERROR_NOT_FOUND, "Not found collaborator");
        }

        landingIsApiService.deleteFile(collaborator.getAccount().getAvatarPath());

        collaboratorRepository.delete(collaborator);
        result.setMessage("Delete Collaborator Sucess");
        return result;
    }
}
