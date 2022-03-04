package com.landingis.api.mapper;


import com.landingis.api.dto.collaboratorProduct.CollaboratorProductDto;
import com.landingis.api.form.collaboratorProduct.CreateCollaboratorProductForm;
import com.landingis.api.form.collaboratorProduct.CreateCollaboratorProductListForm;
import com.landingis.api.storage.model.CollaboratorProduct;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CollaboratorProductMapper {
    @Mapping(source = "collaboratorId", target = "collaborator.id")
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "value", target = "value")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    CollaboratorProduct fromCreateCollaboratorProductFormToEntity(CreateCollaboratorProductForm createCollaboratorProductForm);

    @IterableMapping(elementTargetType = CollaboratorProduct.class, qualifiedByName = "adminCreateMapping")
    List<CollaboratorProduct> fromCreateCollaboratorProductFormToEntityList(List<CreateCollaboratorProductForm> content);


    @Mapping(source = "id", target = "id")

    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "value", target = "value")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminMapping")
    CollaboratorProductDto fromEntityToCollaboratorProductDto(CollaboratorProduct collaboratorProduct);

    @IterableMapping(elementTargetType = CollaboratorProductDto.class, qualifiedByName = "adminMapping")
    @Named("getProductListMapping")
    List<CollaboratorProductDto> fromEntityListToCollaboratorProductDtoList(List<CollaboratorProduct> collaboratorProductList);

}
