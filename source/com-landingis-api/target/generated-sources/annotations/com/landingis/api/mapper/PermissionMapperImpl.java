package com.landingis.api.mapper;

import com.landingis.api.dto.permission.PermissionAdminDto;
import com.landingis.api.dto.permission.PermissionDto;
import com.landingis.api.storage.model.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-26T12:30:30+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class PermissionMapperImpl implements PermissionMapper {

    @Override
    public PermissionDto fromEntityToDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setShowMenu( permission.getShowMenu() );
        permissionDto.setName( permission.getName() );
        permissionDto.setAction( permission.getAction() );
        permissionDto.setDescription( permission.getDescription() );
        permissionDto.setNameGroup( permission.getNameGroup() );
        permissionDto.setStatus( permission.getStatus() );
        permissionDto.setId( permission.getId() );

        return permissionDto;
    }

    @Override
    public PermissionAdminDto fromEntityToAdminDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionAdminDto permissionAdminDto = new PermissionAdminDto();

        permissionAdminDto.setName( permission.getName() );
        permissionAdminDto.setAction( permission.getAction() );
        permissionAdminDto.setDescription( permission.getDescription() );
        permissionAdminDto.setNameGroup( permission.getNameGroup() );
        permissionAdminDto.setShowMenu( permission.getShowMenu() );
        permissionAdminDto.setId( permission.getId() );
        permissionAdminDto.setStatus( permission.getStatus() );
        permissionAdminDto.setModifiedDate( permission.getModifiedDate() );
        permissionAdminDto.setCreatedDate( permission.getCreatedDate() );
        permissionAdminDto.setModifiedBy( permission.getModifiedBy() );
        permissionAdminDto.setCreatedBy( permission.getCreatedBy() );

        return permissionAdminDto;
    }

    @Override
    public List<PermissionAdminDto> fromEntityListToAdminDtoList(List<Permission> content) {
        if ( content == null ) {
            return null;
        }

        List<PermissionAdminDto> list = new ArrayList<PermissionAdminDto>( content.size() );
        for ( Permission permission : content ) {
            list.add( fromEntityToAdminDto( permission ) );
        }

        return list;
    }

    @Override
    public List<PermissionDto> fromEntityToDtoList(List<Permission> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionDto> list1 = new ArrayList<PermissionDto>( list.size() );
        for ( Permission permission : list ) {
            list1.add( fromEntityToDto( permission ) );
        }

        return list1;
    }

    @Override
    public List<PermissionDto> fromEntityListToDtoList(List<Permission> content) {
        if ( content == null ) {
            return null;
        }

        List<PermissionDto> list = new ArrayList<PermissionDto>( content.size() );
        for ( Permission permission : content ) {
            list.add( fromEntityToDto( permission ) );
        }

        return list;
    }
}
