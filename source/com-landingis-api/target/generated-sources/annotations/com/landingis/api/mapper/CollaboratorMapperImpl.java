package com.landingis.api.mapper;

import com.landingis.api.dto.collaborator.CollaboratorDto;
import com.landingis.api.form.collaborator.CreateCollaboratorForm;
import com.landingis.api.form.collaborator.UpdateCollaboratorAdminForm;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Collaborator;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-13T23:37:36+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CollaboratorMapperImpl implements CollaboratorMapper {

    @Override
    public Collaborator fromCreateCollaboratorFormToEntity(CreateCollaboratorForm createCollaboratorForm) {
        if ( createCollaboratorForm == null ) {
            return null;
        }

        Collaborator collaborator = new Collaborator();

        collaborator.setAccount( createCollaboratorFormToAccount( createCollaboratorForm ) );
        collaborator.setBirthday( createCollaboratorForm.getBirthday() );
        collaborator.setNote( createCollaboratorForm.getNote() );
        collaborator.setAddress( createCollaboratorForm.getCollaboratorAddress() );
        collaborator.setSex( createCollaboratorForm.getSex() );
        collaborator.setBranchName( createCollaboratorForm.getBranchName() );
        collaborator.setBankName( createCollaboratorForm.getBankName() );
        collaborator.setDateOfIssue( createCollaboratorForm.getDateOfIssue() );
        collaborator.setPlaceOfIssue( createCollaboratorForm.getPlaceOfIssue() );
        collaborator.setIdentityNumber( createCollaboratorForm.getIdentityNumber() );
        collaborator.setBankNo( createCollaboratorForm.getBankNo() );
        collaborator.setStatus( createCollaboratorForm.getStatus() );

        return collaborator;
    }

    @Override
    public void fromUpdateCollaboratorAdminFormToEntity(UpdateCollaboratorAdminForm updateCollaboratorAdminForm, Collaborator collaborator) {
        if ( updateCollaboratorAdminForm == null ) {
            return;
        }

        if ( collaborator.getAccount() == null ) {
            collaborator.setAccount( new Account() );
        }
        updateCollaboratorAdminFormToAccount( updateCollaboratorAdminForm, collaborator.getAccount() );
        if ( updateCollaboratorAdminForm.getBirthday() != null ) {
            collaborator.setBirthday( updateCollaboratorAdminForm.getBirthday() );
        }
        if ( updateCollaboratorAdminForm.getNote() != null ) {
            collaborator.setNote( updateCollaboratorAdminForm.getNote() );
        }
        if ( updateCollaboratorAdminForm.getCollaboratorAddress() != null ) {
            collaborator.setAddress( updateCollaboratorAdminForm.getCollaboratorAddress() );
        }
        if ( updateCollaboratorAdminForm.getSex() != null ) {
            collaborator.setSex( updateCollaboratorAdminForm.getSex() );
        }
        if ( updateCollaboratorAdminForm.getBranchName() != null ) {
            collaborator.setBranchName( updateCollaboratorAdminForm.getBranchName() );
        }
        if ( updateCollaboratorAdminForm.getBankName() != null ) {
            collaborator.setBankName( updateCollaboratorAdminForm.getBankName() );
        }
        if ( updateCollaboratorAdminForm.getDateOfIssue() != null ) {
            collaborator.setDateOfIssue( updateCollaboratorAdminForm.getDateOfIssue() );
        }
        if ( updateCollaboratorAdminForm.getPlaceOfIssue() != null ) {
            collaborator.setPlaceOfIssue( updateCollaboratorAdminForm.getPlaceOfIssue() );
        }
        if ( updateCollaboratorAdminForm.getIdentityNumber() != null ) {
            collaborator.setIdentityNumber( updateCollaboratorAdminForm.getIdentityNumber() );
        }
        if ( updateCollaboratorAdminForm.getBankNo() != null ) {
            collaborator.setBankNo( updateCollaboratorAdminForm.getBankNo() );
        }
        if ( updateCollaboratorAdminForm.getStatus() != null ) {
            collaborator.setStatus( updateCollaboratorAdminForm.getStatus() );
        }
    }

    @Override
    public CollaboratorDto fromEntityToCollaboratorDto(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }

        CollaboratorDto collaboratorDto = new CollaboratorDto();

        collaboratorDto.setBirthday( collaborator.getBirthday() );
        collaboratorDto.setNote( collaborator.getNote() );
        collaboratorDto.setCollaboratorFullName( collaboratorAccountFullName( collaborator ) );
        collaboratorDto.setCollaboratorAvatarPath( collaboratorAccountAvatarPath( collaborator ) );
        collaboratorDto.setSex( collaborator.getSex() );
        collaboratorDto.setCollaboratorPhone( collaboratorAccountPhone( collaborator ) );
        collaboratorDto.setBranchName( collaborator.getBranchName() );
        collaboratorDto.setBankName( collaborator.getBankName() );
        collaboratorDto.setDateOfIssue( collaborator.getDateOfIssue() );
        collaboratorDto.setCollaboratorAddress( collaborator.getAddress() );
        collaboratorDto.setPlaceOfIssue( collaborator.getPlaceOfIssue() );
        collaboratorDto.setCreatedDate( collaborator.getCreatedDate() );
        collaboratorDto.setIdentityNumber( collaborator.getIdentityNumber() );
        collaboratorDto.setCreatedBy( collaborator.getCreatedBy() );
        collaboratorDto.setBankNo( collaborator.getBankNo() );
        collaboratorDto.setModifiedDate( collaborator.getModifiedDate() );
        collaboratorDto.setModifiedBy( collaborator.getModifiedBy() );
        collaboratorDto.setId( collaborator.getId() );
        collaboratorDto.setCollaboratorEmail( collaboratorAccountEmail( collaborator ) );
        collaboratorDto.setStatus( collaborator.getStatus() );

        return collaboratorDto;
    }

    @Override
    public List<CollaboratorDto> fromEntityListToAdminDtoList(List<Collaborator> content) {
        if ( content == null ) {
            return null;
        }

        List<CollaboratorDto> list = new ArrayList<CollaboratorDto>( content.size() );
        for ( Collaborator collaborator : content ) {
            list.add( fromEntityToCollaboratorDto( collaborator ) );
        }

        return list;
    }

    protected Account createCollaboratorFormToAccount(CreateCollaboratorForm createCollaboratorForm) {
        if ( createCollaboratorForm == null ) {
            return null;
        }

        Account account = new Account();

        account.setAvatarPath( createCollaboratorForm.getCollaboratorAvatarPath() );
        account.setEmail( createCollaboratorForm.getCollaboratorEmail() );
        account.setUsername( createCollaboratorForm.getCollaboratorUsername() );
        account.setFullName( createCollaboratorForm.getCollaboratorFullName() );
        account.setPhone( createCollaboratorForm.getCollaboratorPhone() );

        return account;
    }

    protected void updateCollaboratorAdminFormToAccount(UpdateCollaboratorAdminForm updateCollaboratorAdminForm, Account mappingTarget) {
        if ( updateCollaboratorAdminForm == null ) {
            return;
        }

        if ( updateCollaboratorAdminForm.getCollaboratorAvatarPath() != null ) {
            mappingTarget.setAvatarPath( updateCollaboratorAdminForm.getCollaboratorAvatarPath() );
        }
        if ( updateCollaboratorAdminForm.getCollaboratorEmail() != null ) {
            mappingTarget.setEmail( updateCollaboratorAdminForm.getCollaboratorEmail() );
        }
        if ( updateCollaboratorAdminForm.getCollaboratorFullName() != null ) {
            mappingTarget.setFullName( updateCollaboratorAdminForm.getCollaboratorFullName() );
        }
    }

    private String collaboratorAccountFullName(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }
        Account account = collaborator.getAccount();
        if ( account == null ) {
            return null;
        }
        String fullName = account.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String collaboratorAccountAvatarPath(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }
        Account account = collaborator.getAccount();
        if ( account == null ) {
            return null;
        }
        String avatarPath = account.getAvatarPath();
        if ( avatarPath == null ) {
            return null;
        }
        return avatarPath;
    }

    private String collaboratorAccountPhone(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }
        Account account = collaborator.getAccount();
        if ( account == null ) {
            return null;
        }
        String phone = account.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String collaboratorAccountEmail(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }
        Account account = collaborator.getAccount();
        if ( account == null ) {
            return null;
        }
        String email = account.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }
}
