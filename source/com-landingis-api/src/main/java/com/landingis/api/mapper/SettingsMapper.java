package com.landingis.api.mapper;

import com.landingis.api.dto.settings.SettingsDto;
import com.landingis.api.form.province.UpdateProvinceForm;
import com.landingis.api.form.settings.CreateSettingsForm;
import com.landingis.api.form.settings.UpdateSettingsForm;
import com.landingis.api.storage.model.Province;
import com.landingis.api.storage.model.Settings;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SettingsMapper {
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "editable", target = "editable")
    @Mapping(source = "settingsValue", target = "value")
    @Mapping(source = "settingsGroup", target = "group")
    @Mapping(source = "settingsKey", target = "key")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "groupId", target = "groupId")
    @BeanMapping(ignoreByDefault = true)
    @Named("provinceUpdateMapping")
    Settings fromCreateSettingsFormToEntity(CreateSettingsForm createSettingsForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "settingsValue", target = "value")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("settingsCreateMapping")
    void fromUpdateSettingsFormToEntity(UpdateSettingsForm updateSettingsForm, @MappingTarget Settings settings);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "editable", target = "editable")
    @Mapping(source = "value", target = "settingsValue")
    @Mapping(source = "group", target = "settingsGroup")
    @Mapping(source = "key", target = "settingsKey")
    @Mapping(source = "groupId", target = "groupId")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("settingsMapping")
    SettingsDto fromEntityToSettingsDto(Settings settings);

    @IterableMapping(elementTargetType = SettingsDto.class, qualifiedByName = "settingsMapping")
    List<SettingsDto> fromEntityListToSettingsDtoList(List<Settings> content);
}
