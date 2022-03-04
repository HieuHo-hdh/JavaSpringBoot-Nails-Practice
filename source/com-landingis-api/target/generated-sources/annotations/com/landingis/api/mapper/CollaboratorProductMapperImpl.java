package com.landingis.api.mapper;

import com.landingis.api.dto.collaboratorProduct.CollaboratorProductDto;
import com.landingis.api.form.collaboratorProduct.CreateCollaboratorProductForm;
import com.landingis.api.storage.model.Collaborator;
import com.landingis.api.storage.model.CollaboratorProduct;
import com.landingis.api.storage.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-04T13:17:43+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CollaboratorProductMapperImpl implements CollaboratorProductMapper {

    @Override
    public CollaboratorProduct fromCreateCollaboratorProductFormToEntity(CreateCollaboratorProductForm createCollaboratorProductForm) {
        if ( createCollaboratorProductForm == null ) {
            return null;
        }

        CollaboratorProduct collaboratorProduct = new CollaboratorProduct();

        collaboratorProduct.setCollaborator( createCollaboratorProductFormToCollaborator( createCollaboratorProductForm ) );
        collaboratorProduct.setProduct( createCollaboratorProductFormToProduct( createCollaboratorProductForm ) );
        collaboratorProduct.setKind( createCollaboratorProductForm.getKind() );
        collaboratorProduct.setValue( createCollaboratorProductForm.getValue() );

        return collaboratorProduct;
    }

    @Override
    public List<CollaboratorProduct> fromCreateCollaboratorProductFormToEntityList(List<CreateCollaboratorProductForm> content) {
        if ( content == null ) {
            return null;
        }

        List<CollaboratorProduct> list = new ArrayList<CollaboratorProduct>( content.size() );
        for ( CreateCollaboratorProductForm createCollaboratorProductForm : content ) {
            list.add( fromCreateCollaboratorProductFormToEntity( createCollaboratorProductForm ) );
        }

        return list;
    }

    @Override
    public CollaboratorProductDto fromEntityToCollaboratorProductDto(CollaboratorProduct collaboratorProduct) {
        if ( collaboratorProduct == null ) {
            return null;
        }

        CollaboratorProductDto collaboratorProductDto = new CollaboratorProductDto();

        collaboratorProductDto.setCreatedDate( collaboratorProduct.getCreatedDate() );
        collaboratorProductDto.setCreatedBy( collaboratorProduct.getCreatedBy() );
        collaboratorProductDto.setKind( collaboratorProduct.getKind() );
        collaboratorProductDto.setModifiedDate( collaboratorProduct.getModifiedDate() );
        collaboratorProductDto.setModifiedBy( collaboratorProduct.getModifiedBy() );
        collaboratorProductDto.setId( collaboratorProduct.getId() );
        collaboratorProductDto.setValue( collaboratorProduct.getValue() );
        collaboratorProductDto.setStatus( collaboratorProduct.getStatus() );

        return collaboratorProductDto;
    }

    @Override
    public List<CollaboratorProductDto> fromEntityListToCollaboratorProductDtoList(List<CollaboratorProduct> collaboratorProductList) {
        if ( collaboratorProductList == null ) {
            return null;
        }

        List<CollaboratorProductDto> list = new ArrayList<CollaboratorProductDto>( collaboratorProductList.size() );
        for ( CollaboratorProduct collaboratorProduct : collaboratorProductList ) {
            list.add( fromEntityToCollaboratorProductDto( collaboratorProduct ) );
        }

        return list;
    }

    protected Collaborator createCollaboratorProductFormToCollaborator(CreateCollaboratorProductForm createCollaboratorProductForm) {
        if ( createCollaboratorProductForm == null ) {
            return null;
        }

        Collaborator collaborator = new Collaborator();

        collaborator.setId( createCollaboratorProductForm.getCollaboratorId() );

        return collaborator;
    }

    protected Product createCollaboratorProductFormToProduct(CreateCollaboratorProductForm createCollaboratorProductForm) {
        if ( createCollaboratorProductForm == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( createCollaboratorProductForm.getProductId() );

        return product;
    }
}
