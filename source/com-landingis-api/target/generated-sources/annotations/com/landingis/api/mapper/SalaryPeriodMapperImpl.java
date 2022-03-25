package com.landingis.api.mapper;

import com.landingis.api.dto.salaryPeriod.SalaryPeriodDto;
import com.landingis.api.form.salaryPeriod.CreateSalaryPeriodForm;
import com.landingis.api.form.salaryPeriod.UpdateSalaryPeriodForm;
import com.landingis.api.storage.model.SalaryPeriod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-25T15:28:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class SalaryPeriodMapperImpl implements SalaryPeriodMapper {

    @Override
    public SalaryPeriod fromCreateSalaryPeriodFormToEntity(CreateSalaryPeriodForm createSalaryPeriodForm) {
        if ( createSalaryPeriodForm == null ) {
            return null;
        }

        SalaryPeriod salaryPeriod = new SalaryPeriod();

        salaryPeriod.setEndDate( createSalaryPeriodForm.getEndDate() );
        salaryPeriod.setStartDate( createSalaryPeriodForm.getStartDate() );

        return salaryPeriod;
    }

    @Override
    public SalaryPeriod fromUpdateSalaryPeriodFormToEntity(UpdateSalaryPeriodForm updateSalaryPeriodForm, SalaryPeriod salaryPeriod) {
        if ( updateSalaryPeriodForm == null ) {
            return null;
        }

        if ( updateSalaryPeriodForm.getEndDate() != null ) {
            salaryPeriod.setEndDate( updateSalaryPeriodForm.getEndDate() );
        }
        if ( updateSalaryPeriodForm.getId() != null ) {
            salaryPeriod.setId( updateSalaryPeriodForm.getId() );
        }
        if ( updateSalaryPeriodForm.getStartDate() != null ) {
            salaryPeriod.setStartDate( updateSalaryPeriodForm.getStartDate() );
        }

        return salaryPeriod;
    }

    @Override
    public SalaryPeriodDto fromEntityToSalaryPeriodDto(SalaryPeriod salaryPeriod) {
        if ( salaryPeriod == null ) {
            return null;
        }

        SalaryPeriodDto salaryPeriodDto = new SalaryPeriodDto();

        salaryPeriodDto.setCreatedDate( salaryPeriod.getCreatedDate() );
        salaryPeriodDto.setEndDate( salaryPeriod.getEndDate() );
        salaryPeriodDto.setCreatedBy( salaryPeriod.getCreatedBy() );
        salaryPeriodDto.setModifiedDate( salaryPeriod.getModifiedDate() );
        salaryPeriodDto.setModifiedBy( salaryPeriod.getModifiedBy() );
        salaryPeriodDto.setId( salaryPeriod.getId() );
        salaryPeriodDto.setState( salaryPeriod.getState() );
        salaryPeriodDto.setStartDate( salaryPeriod.getStartDate() );
        salaryPeriodDto.setStatus( salaryPeriod.getStatus() );

        return salaryPeriodDto;
    }

    @Override
    public List<SalaryPeriodDto> fromEntityListToSalaryPeriodDtoList(List<SalaryPeriod> content) {
        if ( content == null ) {
            return null;
        }

        List<SalaryPeriodDto> list = new ArrayList<SalaryPeriodDto>( content.size() );
        for ( SalaryPeriod salaryPeriod : content ) {
            list.add( fromEntityToSalaryPeriodDto( salaryPeriod ) );
        }

        return list;
    }
}
