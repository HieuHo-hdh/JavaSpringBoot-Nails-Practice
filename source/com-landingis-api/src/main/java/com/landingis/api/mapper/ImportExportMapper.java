package com.landingis.api.mapper;

import com.landingis.api.dto.importExport.ImportExportDto;
import com.landingis.api.form.importExport.CreateImportExportForm;
import com.landingis.api.form.importExport.UpdateImportExportForm;
import com.landingis.api.storage.model.ImportExport;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface ImportExportMapper {
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "filePath", target = "filePath")
    @Mapping(source = "money", target = "money")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "kind", target = "kind")
    @BeanMapping(ignoreByDefault = true)
    @Named("importExportCreateMapping")
    ImportExport fromCreateImportExportFormToEntity(CreateImportExportForm createImportExportForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "filePath", target = "filePath")
    @Mapping(source = "money", target = "money")
    @Mapping(source = "note", target = "note")
    @BeanMapping(ignoreByDefault = true)
    @Named("importExportUpdateMapping")
    void fromUpdateImportExportFormToEntity (UpdateImportExportForm updateImportExportForm, @MappingTarget ImportExport importExport);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "filePath", target = "filePath")
    @Mapping(source = "money", target = "money")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "note", target = "note")
    @BeanMapping(ignoreByDefault = true)
    @Named("importExportMapping")
    ImportExportDto fromEntityToImportExportDto(ImportExport importExport);

    @IterableMapping(elementTargetType = ImportExportDto.class, qualifiedByName = "importExportMapping")
    List<ImportExportDto> fromEntityListToImportExportDtoList(List<ImportExport> content);
}
