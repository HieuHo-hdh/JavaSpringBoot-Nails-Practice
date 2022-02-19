package com.landingis.api.mapper;

import com.landingis.api.dto.settings.SettingsDto;
import com.landingis.api.form.settings.CreateSettingsForm;
import com.landingis.api.form.settings.UpdateSettingsForm;
import com.landingis.api.storage.model.Settings;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-19T15:35:35+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class SettingsMapperImpl implements SettingsMapper {

    @Override
    public Settings fromCreateSettingsFormToEntity(CreateSettingsForm createSettingsForm) {
        if ( createSettingsForm == null ) {
            return null;
        }

        Settings settings = new Settings();

        if ( createSettingsForm.getEditable() != null ) {
            settings.setEditable( createSettingsForm.getEditable() );
        }
        settings.setKind( createSettingsForm.getKind() );
        if ( createSettingsForm.getGroupId() != null ) {
            settings.setGroupId( createSettingsForm.getGroupId().intValue() );
        }
        settings.setDescription( createSettingsForm.getDescription() );
        settings.setName( createSettingsForm.getName() );
        settings.setValue( createSettingsForm.getSettingsValue() );
        settings.setKey( createSettingsForm.getSettingsKey() );
        settings.setGroup( createSettingsForm.getSettingsGroup() );

        return settings;
    }

    @Override
    public void fromUpdateSettingsFormToEntity(UpdateSettingsForm updateSettingsForm, Settings settings) {
        if ( updateSettingsForm == null ) {
            return;
        }

        if ( updateSettingsForm.getDescription() != null ) {
            settings.setDescription( updateSettingsForm.getDescription() );
        }
        if ( updateSettingsForm.getId() != null ) {
            settings.setId( updateSettingsForm.getId() );
        }
        if ( updateSettingsForm.getSettingsValue() != null ) {
            settings.setValue( updateSettingsForm.getSettingsValue() );
        }
        if ( updateSettingsForm.getStatus() != null ) {
            settings.setStatus( updateSettingsForm.getStatus() );
        }
    }

    @Override
    public SettingsDto fromEntityToSettingsDto(Settings settings) {
        if ( settings == null ) {
            return null;
        }

        SettingsDto settingsDto = new SettingsDto();

        settingsDto.setEditable( settings.isEditable() );
        settingsDto.setKind( settings.getKind() );
        if ( settings.getGroupId() != null ) {
            settingsDto.setGroupId( settings.getGroupId().longValue() );
        }
        settingsDto.setDescription( settings.getDescription() );
        settingsDto.setCreatedDate( settings.getCreatedDate() );
        settingsDto.setCreatedBy( settings.getCreatedBy() );
        settingsDto.setSettingsGroup( settings.getGroup() );
        settingsDto.setName( settings.getName() );
        settingsDto.setSettingsValue( settings.getValue() );
        settingsDto.setSettingsKey( settings.getKey() );
        settingsDto.setModifiedDate( settings.getModifiedDate() );
        settingsDto.setModifiedBy( settings.getModifiedBy() );
        settingsDto.setId( settings.getId() );
        settingsDto.setStatus( settings.getStatus() );

        return settingsDto;
    }

    @Override
    public List<SettingsDto> fromEntityListToSettingsDtoList(List<Settings> content) {
        if ( content == null ) {
            return null;
        }

        List<SettingsDto> list = new ArrayList<SettingsDto>( content.size() );
        for ( Settings settings : content ) {
            list.add( fromEntityToSettingsDto( settings ) );
        }

        return list;
    }
}
