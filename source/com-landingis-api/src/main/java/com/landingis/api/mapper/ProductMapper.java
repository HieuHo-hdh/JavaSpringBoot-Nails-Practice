package com.landingis.api.mapper;


import com.landingis.api.dto.product.ProductDto;
import com.landingis.api.form.product.UpdateProductForm;
import com.landingis.api.form.product.CreateProductForm;
import com.landingis.api.storage.model.Product;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "productImage", target = "image")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "categoryId", target = "category.id")
//    @Mapping(source = "parentId", target = "parentProduct.id")
    @BeanMapping(ignoreByDefault = true)
    @Named("productCreateMapping")
    Product fromCreateProductFormToEntity(CreateProductForm createProductForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "productName", target = "name")
    @Mapping(source = "productPrice", target = "price")
    @Mapping(source = "productImage", target = "image")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "categoryId", target = "category.id")
    @BeanMapping(ignoreByDefault = true)
    @Named("productUpdateMapping")
    void fromUpdateProductFormToEntity(UpdateProductForm updateProductForm, @MappingTarget Product product);

    //    @Mapping(source = "product", target = "product")


    @Mapping(source = "id", target = "id")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "parentProduct.id", target = "parentId")

    @Mapping(source = "name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "image", target = "image")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "shortDescription", target = "shortDescription")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "labelColor", target = "labelColor")
    @Mapping(source = "productList", target = "productChilds")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("productMapping")
    ProductDto fromEntityToProductDto(Product product);

    @IterableMapping(elementTargetType = ProductDto.class, qualifiedByName = "productMapping")
    List<ProductDto> fromEntityListToProductDtoList(List<Product> content);
}
