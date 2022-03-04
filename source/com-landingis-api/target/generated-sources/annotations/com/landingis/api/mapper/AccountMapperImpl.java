package com.landingis.api.mapper;

import com.landingis.api.dto.account.AccountAdminDto;
import com.landingis.api.dto.account.AccountDto;
import com.landingis.api.dto.group.GroupDto;
import com.landingis.api.dto.permission.PermissionDto;
import com.landingis.api.form.account.CreateAccountAdminForm;
import com.landingis.api.form.account.UpdateAccountAdminForm;
import com.landingis.api.form.account.UpdateProfileAdminForm;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Group;
import com.landingis.api.storage.model.Permission;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-04T11:53:56+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account fromCreateAccountAdminFormToAdmin(CreateAccountAdminForm createAccountAdminForm) {
        if ( createAccountAdminForm == null ) {
            return null;
        }

        Account account = new Account();

        account.setPhone( createAccountAdminForm.getPhone() );
        account.setAvatarPath( createAccountAdminForm.getAvatarPath() );
        account.setFullName( createAccountAdminForm.getFullName() );
        account.setEmail( createAccountAdminForm.getEmail() );
        account.setUsername( createAccountAdminForm.getUsername() );
        account.setStatus( createAccountAdminForm.getStatus() );
        account.setKind( createAccountAdminForm.getKind() );
        account.setPassword( createAccountAdminForm.getPassword() );

        return account;
    }

    @Override
    public void mappingFormUpdateAdminToEntity(UpdateAccountAdminForm updateAccountAdminForm, Account account) {
        if ( updateAccountAdminForm == null ) {
            return;
        }

        if ( updateAccountAdminForm.getFullName() != null ) {
            account.setFullName( updateAccountAdminForm.getFullName() );
        }
        if ( updateAccountAdminForm.getPhone() != null ) {
            account.setPhone( updateAccountAdminForm.getPhone() );
        }
        if ( updateAccountAdminForm.getStatus() != null ) {
            account.setStatus( updateAccountAdminForm.getStatus() );
        }
    }

    @Override
    public void mappingFormUpdateProfileToEntity(UpdateProfileAdminForm updateProfileAdminForm, Account account) {
        if ( updateProfileAdminForm == null ) {
            return;
        }

        if ( updateProfileAdminForm.getFullName() != null ) {
            account.setFullName( updateProfileAdminForm.getFullName() );
        }
        if ( updateProfileAdminForm.getPassword() != null ) {
            account.setPassword( updateProfileAdminForm.getPassword() );
        }
    }

    @Override
    public AccountDto fromEntityToAccountDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setLastLogin( account.getLastLogin() );
        accountDto.setPhone( account.getPhone() );
        if ( account.getKind() != null ) {
            accountDto.setKind( account.getKind() );
        }
        accountDto.setFullName( account.getFullName() );
        accountDto.setIsSuperAdmin( account.getIsSuperAdmin() );
        accountDto.setId( account.getId() );
        accountDto.setAvatar( account.getAvatarPath() );
        accountDto.setEmail( account.getEmail() );
        accountDto.setUsername( account.getUsername() );
        accountDto.setGroup( groupToGroupDto( account.getGroup() ) );

        return accountDto;
    }

    @Override
    public AccountAdminDto fromEntityToAccountAdminDto(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountAdminDto accountAdminDto = new AccountAdminDto();

        accountAdminDto.setLastLogin( account.getLastLogin() );
        accountAdminDto.setKind( account.getKind() );
        accountAdminDto.setFullName( account.getFullName() );
        accountAdminDto.setAvatar( account.getAvatarPath() );
        accountAdminDto.setCreatedDate( account.getCreatedDate() );
        accountAdminDto.setCreatedBy( account.getCreatedBy() );
        accountAdminDto.setPhone( account.getPhone() );
        accountAdminDto.setModifiedBy( account.getModifiedBy() );
        accountAdminDto.setId( account.getId() );
        accountAdminDto.setEmail( account.getEmail() );
        accountAdminDto.setUsername( account.getUsername() );
        accountAdminDto.setGroup( groupToGroupDto( account.getGroup() ) );
        accountAdminDto.setStatus( account.getStatus() );

        return accountAdminDto;
    }

    @Override
    public List<AccountAdminDto> fromEntityListToDtoList(List<Account> content) {
        if ( content == null ) {
            return null;
        }

        List<AccountAdminDto> list = new ArrayList<AccountAdminDto>( content.size() );
        for ( Account account : content ) {
            list.add( fromEntityToAccountAdminDto( account ) );
        }

        return list;
    }

    @Override
    public AccountAdminDto fromEntityToAccountAdminDtoAutoComplete(Account account) {
        if ( account == null ) {
            return null;
        }

        AccountAdminDto accountAdminDto = new AccountAdminDto();

        accountAdminDto.setFullName( account.getFullName() );
        accountAdminDto.setId( account.getId() );

        return accountAdminDto;
    }

    protected PermissionDto permissionToPermissionDto(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDto permissionDto = new PermissionDto();

        permissionDto.setId( permission.getId() );
        permissionDto.setName( permission.getName() );
        permissionDto.setAction( permission.getAction() );
        permissionDto.setShowMenu( permission.getShowMenu() );
        permissionDto.setDescription( permission.getDescription() );
        permissionDto.setNameGroup( permission.getNameGroup() );
        permissionDto.setStatus( permission.getStatus() );

        return permissionDto;
    }

    protected List<PermissionDto> permissionListToPermissionDtoList(List<Permission> list) {
        if ( list == null ) {
            return null;
        }

        List<PermissionDto> list1 = new ArrayList<PermissionDto>( list.size() );
        for ( Permission permission : list ) {
            list1.add( permissionToPermissionDto( permission ) );
        }

        return list1;
    }

    protected GroupDto groupToGroupDto(Group group) {
        if ( group == null ) {
            return null;
        }

        GroupDto groupDto = new GroupDto();

        groupDto.setId( group.getId() );
        groupDto.setName( group.getName() );
        groupDto.setDescription( group.getDescription() );
        groupDto.setKind( group.getKind() );
        groupDto.setPermissions( permissionListToPermissionDtoList( group.getPermissions() ) );

        return groupDto;
    }
}
