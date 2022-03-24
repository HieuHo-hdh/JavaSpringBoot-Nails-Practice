package com.landingis.api.mapper;

import com.landingis.api.dto.collaboratorTotalMoneyReport.CollaboratorTotalMoneyReportDto;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Collaborator;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-24T12:22:32+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class CollaboratorTotalMoneyReportMapperImpl implements CollaboratorTotalMoneyReportMapper {

    @Override
    public CollaboratorTotalMoneyReportDto fromEntityToCollaboratorTotalMoneyReportDto(Collaborator collaborator) {
        if ( collaborator == null ) {
            return null;
        }

        CollaboratorTotalMoneyReportDto collaboratorTotalMoneyReportDto = new CollaboratorTotalMoneyReportDto();

        collaboratorTotalMoneyReportDto.setCollaboratorFullName( collaboratorAccountFullName( collaborator ) );
        collaboratorTotalMoneyReportDto.setCollaboratorId( collaborator.getId() );

        return collaboratorTotalMoneyReportDto;
    }

    @Override
    public List<CollaboratorTotalMoneyReportDto> fromEntityListToCollaboratorTotalMoneyReportDtoList(List<Collaborator> content) {
        if ( content == null ) {
            return null;
        }

        List<CollaboratorTotalMoneyReportDto> list = new ArrayList<CollaboratorTotalMoneyReportDto>( content.size() );
        for ( Collaborator collaborator : content ) {
            list.add( fromEntityToCollaboratorTotalMoneyReportDto( collaborator ) );
        }

        return list;
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
}
