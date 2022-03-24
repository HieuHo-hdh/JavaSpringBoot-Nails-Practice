package com.landingis.api.mapper;

import com.landingis.api.dto.collaboratorTotalMoneyReport.CollaboratorTotalMoneyReportDto;
import com.landingis.api.storage.model.Collaborator;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CollaboratorTotalMoneyReportMapper {
//    @Mapping(source = "id", target = "id")
//    @Mapping(source = "orders.id", target = "ordersId")
    @Mapping(source = "id", target = "collaboratorId")
    @Mapping(source = "account.fullName", target = "collaboratorFullName")
//    @Mapping(source = "totalMoney", target = "totalMoney")
    @BeanMapping(ignoreByDefault = true)
    @Named("collaboratorTotalMoneyReportMapping")
    CollaboratorTotalMoneyReportDto fromEntityToCollaboratorTotalMoneyReportDto(Collaborator collaborator);

    @IterableMapping(elementTargetType = CollaboratorTotalMoneyReportDto.class, qualifiedByName = "collaboratorTotalMoneyReportMapping")
    List<CollaboratorTotalMoneyReportDto> fromEntityListToCollaboratorTotalMoneyReportDtoList(List<Collaborator> content);
}
