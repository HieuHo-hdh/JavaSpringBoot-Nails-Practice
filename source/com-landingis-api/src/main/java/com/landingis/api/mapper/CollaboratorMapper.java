package com.landingis.api.mapper;

import com.landingis.api.dto.collaborator.CollaboratorDto;
import com.landingis.api.form.collaborator.CreateCollaboratorForm;
import com.landingis.api.form.collaborator.UpdateCollaboratorAdminForm;
import com.landingis.api.storage.model.Collaborator;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CollaboratorMapper {
    @Mapping(source = "collaboratorEmail", target = "account.email")
    @Mapping(source = "collaboratorFullName", target = "account.fullName")
    @Mapping(source = "collaboratorUsername", target = "account.username")
    @Mapping(source = "collaboratorPhone", target = "account.phone")
    @Mapping(source = "collaboratorAvatarPath", target = "account.avatarPath")
    @Mapping(source = "collaboratorAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    Collaborator fromCreateCollaboratorFormToEntity(CreateCollaboratorForm createCollaboratorForm);

    @Mapping(source = "collaboratorEmail", target = "account.email")
    @Mapping(source = "collaboratorFullName", target = "account.fullName")
    @Mapping(source = "collaboratorAvatarPath", target = "account.avatarPath")
    @Mapping(source = "collaboratorAddress", target = "address")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateCollaboratorAdminFormToEntity(UpdateCollaboratorAdminForm updateCollaboratorAdminForm, @MappingTarget Collaborator collaborator);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "account.email", target = "collaboratorEmail")
    @Mapping(source = "account.fullName", target = "collaboratorFullName")
//    @Mapping(source = "account.password", target = "password")
    @Mapping(source = "account.phone", target = "collaboratorPhone")
    @Mapping(source = "account.avatarPath", target = "collaboratorAvatarPath")
    @Mapping(source = "address", target = "collaboratorAddress")
    @Mapping(source = "birthday", target = "birthday")
    @Mapping(source = "sex", target = "sex")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "identityNumber", target = "identityNumber")
    @Mapping(source = "dateOfIssue", target = "dateOfIssue")
    @Mapping(source = "placeOfIssue", target = "placeOfIssue")
    @Mapping(source = "bankNo", target = "bankNo")
    @Mapping(source = "bankName", target = "bankName")
    @Mapping(source = "branchName", target = "branchName")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminMapping")
    CollaboratorDto fromEntityToCollaboratorDto(Collaborator collaborator);

    @IterableMapping(elementTargetType = CollaboratorDto.class, qualifiedByName = "adminMapping")
    List<CollaboratorDto> fromEntityListToAdminDtoList(List<Collaborator> content);
}
