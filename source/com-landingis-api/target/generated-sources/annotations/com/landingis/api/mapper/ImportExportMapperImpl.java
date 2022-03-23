package com.landingis.api.mapper;

import com.landingis.api.dto.importExport.ImportExportDto;
import com.landingis.api.form.importExport.CreateImportExportForm;
import com.landingis.api.form.importExport.UpdateImportExportForm;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.ImportExport;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-23T15:03:00+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class ImportExportMapperImpl implements ImportExportMapper {

    @Override
    public ImportExport fromCreateImportExportFormToEntity(CreateImportExportForm createImportExportForm) {
        if ( createImportExportForm == null ) {
            return null;
        }

        ImportExport importExport = new ImportExport();

        importExport.setCategory( createImportExportFormToCategory( createImportExportForm ) );
        importExport.setNote( createImportExportForm.getNote() );
        importExport.setCode( createImportExportForm.getCode() );
        importExport.setKind( createImportExportForm.getKind() );
        importExport.setFilePath( createImportExportForm.getFilePath() );
        importExport.setMoney( createImportExportForm.getMoney() );

        return importExport;
    }

    @Override
    public void fromUpdateImportExportFormToEntity(UpdateImportExportForm updateImportExportForm, ImportExport importExport) {
        if ( updateImportExportForm == null ) {
            return;
        }

        if ( updateImportExportForm.getNote() != null ) {
            importExport.setNote( updateImportExportForm.getNote() );
        }
        if ( updateImportExportForm.getCode() != null ) {
            importExport.setCode( updateImportExportForm.getCode() );
        }
        if ( updateImportExportForm.getFilePath() != null ) {
            importExport.setFilePath( updateImportExportForm.getFilePath() );
        }
        if ( updateImportExportForm.getMoney() != null ) {
            importExport.setMoney( updateImportExportForm.getMoney() );
        }
        if ( updateImportExportForm.getId() != null ) {
            importExport.setId( updateImportExportForm.getId() );
        }
    }

    @Override
    public ImportExportDto fromEntityToImportExportDto(ImportExport importExport) {
        if ( importExport == null ) {
            return null;
        }

        ImportExportDto importExportDto = new ImportExportDto();

        importExportDto.setNote( importExport.getNote() );
        importExportDto.setCode( importExport.getCode() );
        importExportDto.setMoney( importExport.getMoney() );
        importExportDto.setKind( importExport.getKind() );
        importExportDto.setFilePath( importExport.getFilePath() );
        importExportDto.setId( importExport.getId() );
        importExportDto.setCategoryId( importExportCategoryId( importExport ) );

        return importExportDto;
    }

    @Override
    public List<ImportExportDto> fromEntityListToImportExportDtoList(List<ImportExport> content) {
        if ( content == null ) {
            return null;
        }

        List<ImportExportDto> list = new ArrayList<ImportExportDto>( content.size() );
        for ( ImportExport importExport : content ) {
            list.add( fromEntityToImportExportDto( importExport ) );
        }

        return list;
    }

    protected Category createImportExportFormToCategory(CreateImportExportForm createImportExportForm) {
        if ( createImportExportForm == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( createImportExportForm.getCategoryId() );

        return category;
    }

    private Long importExportCategoryId(ImportExport importExport) {
        if ( importExport == null ) {
            return null;
        }
        Category category = importExport.getCategory();
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
