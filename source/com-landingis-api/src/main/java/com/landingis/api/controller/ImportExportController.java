package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.importExport.ImportExportDto;
import com.landingis.api.dto.importExport.ImportExportResponseListObj;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.importExport.CreateImportExportForm;
import com.landingis.api.form.importExport.UpdateImportExportForm;
import com.landingis.api.mapper.ImportExportMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.ImportExportCriteria;
import com.landingis.api.storage.model.*;
import com.landingis.api.storage.model.ImportExport;
import com.landingis.api.storage.repository.CategoryRepository;
import com.landingis.api.storage.repository.ImportExportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/import-export")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ImportExportController extends  ABasicController{
    @Autowired
    ImportExportRepository importExportRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ImportExportMapper importExportMapper;
    
    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ImportExportResponseListObj<ImportExportDto>> getList(ImportExportCriteria importExportCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_UNAUTHORIZED, "Not allow get list");
        }
        ApiMessageDto<ImportExportResponseListObj<ImportExportDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<ImportExport> listImportExport = importExportRepository.findAll(importExportCriteria.getSpecification(), pageable);

        ImportExportResponseListObj<ImportExportDto> responseListObj = new ImportExportResponseListObj<>();
        List<ImportExportDto> importExportDtoList = importExportMapper.fromEntityListToImportExportDtoList(listImportExport.getContent());
        Double sum = 0d;
        Integer importExportKind = importExportCriteria.getKind();
        Date fromDate = importExportCriteria.getFrom();
        Date toDate = importExportCriteria.getTo();
        if (importExportKind != null && fromDate != null && toDate!= null)
        {
            sum = importExportRepository.getImportExportFromDateToDate(importExportKind, fromDate, toDate);
        }
        else if (importExportKind != null && fromDate != null)
        {
            sum = importExportRepository.getImportExportFromDate(importExportKind, fromDate);
        }
        else if (importExportKind != null && toDate!= null)
        {
            sum = importExportRepository.getImportExportToDate(importExportKind, toDate);
        }
        else if (importExportKind != null)
        {
            sum = importExportRepository.getImportExport(importExportKind);
        }
        responseListObj.setData(importExportDtoList);
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listImportExport.getTotalPages());
        responseListObj.setTotalElements(listImportExport.getTotalElements());
        responseListObj.setSum(sum);
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("List importExport success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ImportExportDto> get(@PathVariable("id") Long id)
    {
        if (!isAdmin())
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_UNAUTHORIZED, "Not allow get importExport");
        }
        ApiMessageDto<ImportExportDto> result = new ApiMessageDto<>();
        ImportExport importExport = importExportRepository.findById(id).orElse(null);

        if (importExport == null)
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_NOT_FOUND, "Not found importExport");
        }
        result.setData(importExportMapper.fromEntityToImportExportDto(importExport));
        result.setMessage("Set ImportExport Success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateImportExportForm createImportExportForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Category category = categoryRepository.findById(createImportExportForm.getCategoryId()).orElse(null);

        if (category == null)
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_NOT_FOUND_CATEGORY, "Not found category");
        }

        ImportExport importExport = importExportMapper.fromCreateImportExportFormToEntity(createImportExportForm);
        importExportRepository.save(importExport);
        apiMessageDto.setMessage("Create importExport success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateImportExportForm updateImportExportForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        ImportExport importExport = importExportRepository.findById(updateImportExportForm.getId()).orElse(null);
        if (importExport == null)
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_NOT_FOUND, "Not found importExport");
        }

        importExportMapper.fromUpdateImportExportFormToEntity(updateImportExportForm, importExport);
        importExportRepository.save(importExport);
        apiMessageDto.setMessage("Update importExport success");
        return apiMessageDto;
    }
    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<ImportExportDto> delete(@Valid @PathVariable("id") Long id)
    {
        if(!isAdmin())
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_UNAUTHORIZED, "Not allow to delete");
        }
        ApiMessageDto<ImportExportDto> result = new ApiMessageDto<>();
        ImportExport importExport = importExportRepository.findById(id).orElse(null);

        if (importExport == null)
        {
            throw new RequestException(ErrorCode.IMPORTEXPORT_ERROR_NOT_FOUND, "Not found importExport");
        }
        
        importExportRepository.delete(importExport);
        result.setMessage("Delete ImportExport Sucess");
        return result;
    }
    
}
