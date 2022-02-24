package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.product.ProductDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.product.UpdateProductForm;
import com.landingis.api.form.product.CreateProductForm;
import com.landingis.api.form.product.UpdateProductForm;
import com.landingis.api.mapper.ProductMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.ProductCriteria;
import com.landingis.api.storage.model.Product;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.Product;
import com.landingis.api.storage.repository.CategoryRepository;
import com.landingis.api.storage.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class ProductController extends ABasicController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ProductDto>> list(ProductCriteria productCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_UNAUTHORIZED, "Not allowed get list.");
        }
        ApiMessageDto<ResponseListObj<ProductDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Product> listProduct = productRepository.findAll(productCriteria.getSpecification(), pageable);
        ResponseListObj<ProductDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(productMapper.fromEntityListToProductDtoList(listProduct.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listProduct.getTotalPages());
        responseListObj.setTotalElements(listProduct.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/auto-complete", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<ProductDto>> autocomplete(ProductCriteria productCriteria) {
        ApiMessageDto<ResponseListObj<ProductDto>> responseListObjApiMessageDto = new ApiMessageDto<>();
        Page<Product> listProduct = productRepository.findAll(productCriteria.getSpecification(), Pageable.unpaged()); //Use Pageable.unpaged()
        ResponseListObj<ProductDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(productMapper.fromEntityListToProductDtoAutoComplete(listProduct.getContent()));
        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get auto-complete success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ProductDto> get(@PathVariable("id") Long id) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.PRODUCT_ERROR_UNAUTHORIZED, "Not allowed get.");
        }
        ApiMessageDto<ProductDto> result = new ApiMessageDto<>();

        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND, "Not found product.");
        }
        result.setData(productMapper.fromEntityToProductDto(product));
        result.setMessage("Get product success");
        return result;
    }


    @GetMapping(value = "/client-get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ProductDto> clientGet(@NotBlank @RequestParam Long productId,
                                               @NotBlank @RequestParam Long collaboratorId)
    {
        ApiMessageDto<ProductDto> result = new ApiMessageDto<>();

        Product product = productRepository.findById(productId).orElse(null);
        if (product == null)
        {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND, "Not found product.");
        }
        result.setData(productMapper.fromEntityToClientProductDto(product));
        result.setMessage("Get product success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateProductForm createProductForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.PRODUCT_ERROR_UNAUTHORIZED, "Not allowed to create.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Category category = categoryRepository.findById(createProductForm.getCategoryId()).orElse(null);
        if(category == null || !Objects.equals(category.getStatus(), LandingISConstant.STATUS_ACTIVE)) {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND_CATEGORY, "Not found category.");
        }

        Product product = productMapper.fromCreateProductFormToEntity(createProductForm);

        //Find if exists parent Product
        if(createProductForm.getParentId() != null) {
            Product parentProduct = productRepository.findById(createProductForm.getParentId()).orElse(null);
            if(parentProduct == null || parentProduct.getParentProduct() != null) {
                throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND, "Not found product parent");
            }
            else
            {
                parentProduct.setHasChild(true);
                productRepository.save(parentProduct);
            }
            product.setParentProduct(parentProduct);
        }
        productRepository.save(product);
        apiMessageDto.setMessage("Create product success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateProductForm updateProductForm, BindingResult bindingResult) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_UNAUTHORIZED, "Not allow to create");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Product product = productRepository.findById(updateProductForm.getId()).orElse(null);
        if(product == null)
        {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND, "Not found product.");
        }

        Category category = categoryRepository.findById(updateProductForm.getCategoryId()).orElse(null);
        if(category == null || !Objects.equals(category.getStatus(), LandingISConstant.STATUS_ACTIVE)) {
            throw new RequestException(ErrorCode.PRODUCT_ERROR_NOT_FOUND_CATEGORY, "Not found category.");
        }

        if(StringUtils.isNoneBlank(product.getImage()) && !updateProductForm.getProductImage().equals(product.getImage())) {
            landingIsApiService.deleteFile(product.getImage());
        }

        productMapper.fromUpdateProductFormToEntity(updateProductForm, product);
        productRepository.save(product);
        apiMessageDto.setMessage("Update product success");
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
        Product product = productRepository.findById(id).orElse(null);

        if (product == null)
        {
            throw new RequestException(ErrorCode.ADDRESS_ERROR_NOT_FOUND, "Not found product");
        }
        Product parentProduct = product.getParentProduct();
        if(parentProduct != null)
        {
            if (parentProduct.getProductList().size() <= 1)
            {
                parentProduct.setHasChild(false);
                productRepository.save(parentProduct);
            }
        }

        landingIsApiService.deleteFile(product.getImage());
        productRepository.delete(product);
        result.setMessage("Delete Product Success");
        return result;
    }

}
