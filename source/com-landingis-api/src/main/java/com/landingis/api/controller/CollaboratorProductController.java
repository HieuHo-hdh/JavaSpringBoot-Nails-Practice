package com.landingis.api.controller;


import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.collaboratorProduct.CollaboratorProductDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.collaboratorProduct.CreateCollaboratorProductForm;
import com.landingis.api.form.collaboratorProduct.CreateCollaboratorProductListForm;
import com.landingis.api.form.collaboratorProduct.UpdateCollaboratorProductForm;
import com.landingis.api.form.collaboratorProduct.UpdateCollaboratorProductListForm;
import com.landingis.api.mapper.CollaboratorProductMapper;
import com.landingis.api.storage.criteria.CollaboratorProductCriteria;
import com.landingis.api.storage.model.CollaboratorProduct;
import com.landingis.api.storage.repository.CollaboratorProductRepository;
import com.landingis.api.storage.repository.CollaboratorRepository;
import com.landingis.api.storage.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/collaborator-product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CollaboratorProductController extends ABasicController {

    @Autowired
    CollaboratorProductRepository collaboratorProductRepository;

    @Autowired
    CollaboratorRepository collaboratorRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CollaboratorProductMapper collaboratorProductMapper;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CollaboratorProductDto>> list(CollaboratorProductCriteria collaboratorProductCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_UNAUTHORIZED, "Not allowed get list.");
        }
        ApiMessageDto<ResponseListObj<CollaboratorProductDto>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<CollaboratorProduct> listCollaboratorProduct = collaboratorProductRepository.findAll(collaboratorProductCriteria.getSpecification(), pageable);
        ResponseListObj<CollaboratorProductDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(collaboratorProductMapper.fromEntityListToCollaboratorProductDtoList(listCollaboratorProduct.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listCollaboratorProduct.getTotalPages());
        responseListObj.setTotalElements(listCollaboratorProduct.getTotalElements());
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CollaboratorProductDto> get(@PathVariable("id") Long id) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_UNAUTHORIZED, "Not allowed get.");
        }
        ApiMessageDto<CollaboratorProductDto> result = new ApiMessageDto<>();

        CollaboratorProduct collaboratorProduct = collaboratorProductRepository.findById(id).orElse(null);
        if (collaboratorProduct == null) {
            throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_NOT_FOUND, "Not found collaborator product.");
        }
        result.setData(collaboratorProductMapper.fromEntityToCollaboratorProductDto(collaboratorProduct));
        result.setMessage("Get product success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCollaboratorProductListForm createCollaboratorProductListForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_UNAUTHORIZED, "Not allowed to create.");
        }

        for (Integer i = 0; i < createCollaboratorProductListForm.getCollaboratorProducts().size(); i++) {
            CreateCollaboratorProductForm createCollaboratorProductForm = createCollaboratorProductListForm.getCollaboratorProducts().get(i);
            if (productRepository.findById(createCollaboratorProductForm.getProductId()).orElse(null) == null) {
                throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_NOT_FOUND_PRODUCT, "Not found product.");
            }
            if (collaboratorRepository.findById(createCollaboratorProductForm.getCollaboratorId()).orElse(null) == null) {
                throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_NOT_FOUND_COLLABORATOR, "Not found collaborator.");
            }

            if (createCollaboratorProductForm.getKind() == LandingISConstant.COLLABORATOR_PRODUCT_KIND_PERCENT_VALUE) {
                if (createCollaboratorProductForm.getValue() < 0 || createCollaboratorProductForm.getValue() > 100)
                    throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_VALUE, "Invalid value");
            } else if (createCollaboratorProductForm.getKind() == LandingISConstant.COLLABORATOR_PRODUCT_KIND_MONEY_VALUE) {
                if (createCollaboratorProductForm.getValue() < 0)
                    throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_VALUE, "Invalid value");
            } else throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_KIND, "Invalid kind");
        }
        List<CollaboratorProduct> collaboratorProducts = collaboratorProductMapper.fromCreateCollaboratorProductFormToEntityList(createCollaboratorProductListForm.getCollaboratorProducts());
        collaboratorProductRepository.saveAll(collaboratorProducts);
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        apiMessageDto.setMessage("Create product success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCollaboratorProductListForm updateCollaboratorProductListForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_UNAUTHORIZED);
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        for (Integer i = 0; i < updateCollaboratorProductListForm.getCollaboratorProducts().size(); i++) {
            UpdateCollaboratorProductForm updateCollaboratorProductForm = updateCollaboratorProductListForm.getCollaboratorProducts().get(i);
            CollaboratorProduct collaboratorProduct = collaboratorProductRepository.findById(updateCollaboratorProductForm.getId()).orElse(null);
            if (collaboratorProduct == null) {
                throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_NOT_FOUND, "Not found collaborator product.");
            }
            if (updateCollaboratorProductForm.getKind() == LandingISConstant.COLLABORATOR_PRODUCT_KIND_PERCENT_VALUE) {
                if (updateCollaboratorProductForm.getValue() < 0 || updateCollaboratorProductForm.getValue() > 100)
                    throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_VALUE, "Invalid value");
            } else if (updateCollaboratorProductForm.getKind() == LandingISConstant.COLLABORATOR_PRODUCT_KIND_MONEY_VALUE) {
                if (updateCollaboratorProductForm.getValue() < 0)
                    throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_VALUE, "Invalid value");
            } else throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_INVALID_KIND, "Invalid kind");
            collaboratorProductMapper.fromUpdateCollaboratorProductFormToEntity(updateCollaboratorProductForm, collaboratorProduct);
            collaboratorProductRepository.save(collaboratorProduct);
        }
        apiMessageDto.setMessage("Update collaborator product success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@Valid @RequestBody List<Long> ids) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_UNAUTHORIZED);
        }
        ids.forEach(id -> {
            CollaboratorProduct collaboratorProduct = collaboratorProductRepository.findById(id).orElse(null);
            if(collaboratorProduct == null){
                throw new RequestException(ErrorCode.COLLABORATOR_PRODUCT_ERROR_NOT_FOUND);
            }
            collaboratorProductRepository.delete(collaboratorProduct);
        });
        ApiMessageDto<String> result = new ApiMessageDto<>();
        result.setMessage("Delete success");
        return result;
    }
}
