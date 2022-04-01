package com.landingis.api.mapper;

import com.landingis.api.dto.salary.SalaryDto;
import com.landingis.api.storage.model.Salary;
import com.landingis.api.storage.model.SalaryPeriod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-31T15:44:53+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class SalaryMapperImpl implements SalaryMapper {

    @Override
    public SalaryDto fromEntityToSalaryDto(Salary Salary) {
        if ( Salary == null ) {
            return null;
        }

        SalaryDto salaryDto = new SalaryDto();

        salaryDto.setSalaryPrice( Salary.getSalaryPrice() );
        salaryDto.setSalaryPeriodId( salarySalaryPeriodId( Salary ) );
        salaryDto.setIsPayment( Salary.getIsPayment() );
        salaryDto.setRevenue( Salary.getRevenue() );
        salaryDto.setCreatedDate( Salary.getCreatedDate() );
        salaryDto.setCreatedBy( Salary.getCreatedBy() );
        salaryDto.setModifiedDate( Salary.getModifiedDate() );
        salaryDto.setCommission( Salary.getCommission() );
        salaryDto.setModifiedBy( Salary.getModifiedBy() );
        salaryDto.setId( Salary.getId() );
        salaryDto.setPayDate( Salary.getPayDate() );
        salaryDto.setStatus( Salary.getStatus() );

        return salaryDto;
    }

    @Override
    public List<SalaryDto> fromEntityListToSalaryDtoList(List<Salary> content) {
        if ( content == null ) {
            return null;
        }

        List<SalaryDto> list = new ArrayList<SalaryDto>( content.size() );
        for ( Salary salary : content ) {
            list.add( fromEntityToSalaryDto( salary ) );
        }

        return list;
    }

    private Long salarySalaryPeriodId(Salary salary) {
        if ( salary == null ) {
            return null;
        }
        SalaryPeriod salaryPeriod = salary.getSalaryPeriod();
        if ( salaryPeriod == null ) {
            return null;
        }
        Long id = salaryPeriod.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
