package com.landingis.api.mapper;

import com.landingis.api.dto.product.ProductDto;
import com.landingis.api.form.product.CreateProductForm;
import com.landingis.api.form.product.UpdateProductForm;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-26T12:30:29+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product fromCreateProductFormToEntity(CreateProductForm createProductForm) {
        if ( createProductForm == null ) {
            return null;
        }

        Product product = new Product();

        product.setCategory( createProductFormToCategory( createProductForm ) );
        product.setImage( createProductForm.getProductImage() );
        product.setDescription( createProductForm.getDescription() );
        product.setShortDescription( createProductForm.getShortDescription() );
        product.setLabelColor( createProductForm.getLabelColor() );
        product.setPrice( createProductForm.getProductPrice() );
        product.setName( createProductForm.getProductName() );
        product.setSaleOff( createProductForm.getSaleOff() );
        product.setStatus( createProductForm.getStatus() );

        return product;
    }

    @Override
    public void fromUpdateProductFormToEntity(UpdateProductForm updateProductForm, Product product) {
        if ( updateProductForm == null ) {
            return;
        }

        if ( updateProductForm.getProductImage() != null ) {
            product.setImage( updateProductForm.getProductImage() );
        }
        if ( updateProductForm.getDescription() != null ) {
            product.setDescription( updateProductForm.getDescription() );
        }
        if ( updateProductForm.getShortDescription() != null ) {
            product.setShortDescription( updateProductForm.getShortDescription() );
        }
        if ( updateProductForm.getLabelColor() != null ) {
            product.setLabelColor( updateProductForm.getLabelColor() );
        }
        if ( updateProductForm.getProductPrice() != null ) {
            product.setPrice( updateProductForm.getProductPrice() );
        }
        if ( updateProductForm.getProductName() != null ) {
            product.setName( updateProductForm.getProductName() );
        }
        if ( updateProductForm.getId() != null ) {
            product.setId( updateProductForm.getId() );
        }
        if ( updateProductForm.getSaleOff() != null ) {
            product.setSaleOff( updateProductForm.getSaleOff() );
        }
        if ( updateProductForm.getStatus() != null ) {
            product.setStatus( updateProductForm.getStatus() );
        }
    }

    @Override
    public ProductDto fromEntityToProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setImage( product.getImage() );
        productDto.setDescription( product.getDescription() );
        productDto.setShortDescription( product.getShortDescription() );
        Long id = productParentProductId( product );
        if ( id != null ) {
            productDto.setParentId( id.intValue() );
        }
        productDto.setLabelColor( product.getLabelColor() );
        productDto.setProductChilds( fromEntityListToProductDtoList( product.getProductList() ) );
        productDto.setCreatedDate( product.getCreatedDate() );
        productDto.setCreatedBy( product.getCreatedBy() );
        productDto.setPrice( product.getPrice() );
        productDto.setHasChild( product.getHasChild() );
        productDto.setName( product.getName() );
        productDto.setModifiedDate( product.getModifiedDate() );
        productDto.setModifiedBy( product.getModifiedBy() );
        productDto.setId( product.getId() );
        productDto.setSaleOff( product.getSaleOff() );
        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setStatus( product.getStatus() );

        return productDto;
    }

    @Override
    public ProductDto fromEntityToClientProductDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setImage( product.getImage() );
        productDto.setDescription( product.getDescription() );
        productDto.setShortDescription( product.getShortDescription() );
        Long id = productParentProductId( product );
        if ( id != null ) {
            productDto.setParentId( id.intValue() );
        }
        productDto.setLabelColor( product.getLabelColor() );
        productDto.setProductChilds( fromEntityListToProductDtoList( product.getProductList() ) );
        productDto.setCreatedDate( product.getCreatedDate() );
        productDto.setCreatedBy( product.getCreatedBy() );
        productDto.setPrice( product.getPrice() );
        productDto.setHasChild( product.getHasChild() );
        productDto.setName( product.getName() );
        productDto.setModifiedDate( product.getModifiedDate() );
        productDto.setModifiedBy( product.getModifiedBy() );
        productDto.setId( product.getId() );
        productDto.setSaleOff( product.getSaleOff() );
        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setStatus( product.getStatus() );

        return productDto;
    }

    @Override
    public ProductDto fromEntityListToProductDtoListNotDescription(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setImage( product.getImage() );
        productDto.setShortDescription( product.getShortDescription() );
        Long id = productParentProductId( product );
        if ( id != null ) {
            productDto.setParentId( id.intValue() );
        }
        productDto.setLabelColor( product.getLabelColor() );
        productDto.setProductChilds( fromEntityListToProductDtoList( product.getProductList() ) );
        productDto.setCreatedDate( product.getCreatedDate() );
        productDto.setCreatedBy( product.getCreatedBy() );
        productDto.setPrice( product.getPrice() );
        productDto.setHasChild( product.getHasChild() );
        productDto.setName( product.getName() );
        productDto.setModifiedDate( product.getModifiedDate() );
        productDto.setModifiedBy( product.getModifiedBy() );
        productDto.setId( product.getId() );
        productDto.setSaleOff( product.getSaleOff() );
        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setStatus( product.getStatus() );

        return productDto;
    }

    @Override
    public List<ProductDto> fromEntityListToProductDtoList(List<Product> content) {
        if ( content == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( content.size() );
        for ( Product product : content ) {
            list.add( fromEntityListToProductDtoListNotDescription( product ) );
        }

        return list;
    }

    @Override
    public ProductDto fromEntityListToProductDtoAutoComplete(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setImage( product.getImage() );
        productDto.setShortDescription( product.getShortDescription() );
        Long id = productParentProductId( product );
        if ( id != null ) {
            productDto.setParentId( id.intValue() );
        }
        productDto.setLabelColor( product.getLabelColor() );
        productDto.setProductChilds( fromEntityListToProductDtoAutoComplete( product.getProductList() ) );
        productDto.setCreatedDate( product.getCreatedDate() );
        productDto.setCreatedBy( product.getCreatedBy() );
        productDto.setPrice( product.getPrice() );
        productDto.setHasChild( product.getHasChild() );
        productDto.setName( product.getName() );
        productDto.setModifiedDate( product.getModifiedDate() );
        productDto.setModifiedBy( product.getModifiedBy() );
        productDto.setId( product.getId() );
        productDto.setSaleOff( product.getSaleOff() );
        productDto.setCategoryId( productCategoryId( product ) );
        productDto.setStatus( product.getStatus() );

        return productDto;
    }

    @Override
    public List<ProductDto> fromEntityListToProductDtoAutoComplete(List<Product> content) {
        if ( content == null ) {
            return null;
        }

        List<ProductDto> list = new ArrayList<ProductDto>( content.size() );
        for ( Product product : content ) {
            list.add( fromEntityListToProductDtoAutoComplete( product ) );
        }

        return list;
    }

    protected Category createProductFormToCategory(CreateProductForm createProductForm) {
        if ( createProductForm == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( createProductForm.getCategoryId() );

        return category;
    }

    private Long productParentProductId(Product product) {
        if ( product == null ) {
            return null;
        }
        Product parentProduct = product.getParentProduct();
        if ( parentProduct == null ) {
            return null;
        }
        Long id = parentProduct.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long productCategoryId(Product product) {
        if ( product == null ) {
            return null;
        }
        Category category = product.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
