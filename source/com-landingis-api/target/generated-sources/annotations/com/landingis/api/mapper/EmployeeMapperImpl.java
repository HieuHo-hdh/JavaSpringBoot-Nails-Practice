package com.landingis.api.mapper;

import com.landingis.api.dto.employee.EmployeeDto;
import com.landingis.api.form.employee.CreateEmployeeForm;
import com.landingis.api.form.employee.UpdateEmployeeAdminForm;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Employee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-02-13T14:59:10+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public Employee fromCreateEmployeeFormToEntity(CreateEmployeeForm createEmployeeForm) {
        if ( createEmployeeForm == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setAccount( createEmployeeFormToAccount( createEmployeeForm ) );
        employee.setBirthday( createEmployeeForm.getBirthday() );
        employee.setNote( createEmployeeForm.getNote() );
        employee.setAddress( createEmployeeForm.getEmployeeAddress() );
        employee.setSex( createEmployeeForm.getSex() );
        employee.setBranchName( createEmployeeForm.getBranchName() );
        employee.setBankName( createEmployeeForm.getBankName() );
        employee.setDateOfIssue( createEmployeeForm.getDateOfIssue() );
        employee.setSalary( createEmployeeForm.getSalary() );
        employee.setLabelColor( createEmployeeForm.getLabelColor() );
        employee.setPlaceOfIssue( createEmployeeForm.getPlaceOfIssue() );
        employee.setIdentityNumber( createEmployeeForm.getIdentityNumber() );
        employee.setBankNo( createEmployeeForm.getBankNo() );
        employee.setStatus( createEmployeeForm.getStatus() );

        return employee;
    }

    @Override
    public void fromUpdateEmployeeAdminFormToEntity(UpdateEmployeeAdminForm updateEmployeeAdminForm, Employee employee) {
        if ( updateEmployeeAdminForm == null ) {
            return;
        }

        if ( employee.getAccount() == null ) {
            employee.setAccount( new Account() );
        }
        updateEmployeeAdminFormToAccount( updateEmployeeAdminForm, employee.getAccount() );
        if ( updateEmployeeAdminForm.getBirthday() != null ) {
            employee.setBirthday( updateEmployeeAdminForm.getBirthday() );
        }
        if ( updateEmployeeAdminForm.getNote() != null ) {
            employee.setNote( updateEmployeeAdminForm.getNote() );
        }
        if ( updateEmployeeAdminForm.getEmployeeAddress() != null ) {
            employee.setAddress( updateEmployeeAdminForm.getEmployeeAddress() );
        }
        if ( updateEmployeeAdminForm.getSex() != null ) {
            employee.setSex( updateEmployeeAdminForm.getSex() );
        }
        if ( updateEmployeeAdminForm.getBranchName() != null ) {
            employee.setBranchName( updateEmployeeAdminForm.getBranchName() );
        }
        if ( updateEmployeeAdminForm.getBankName() != null ) {
            employee.setBankName( updateEmployeeAdminForm.getBankName() );
        }
        if ( updateEmployeeAdminForm.getDateOfIssue() != null ) {
            employee.setDateOfIssue( updateEmployeeAdminForm.getDateOfIssue() );
        }
        if ( updateEmployeeAdminForm.getSalary() != null ) {
            employee.setSalary( updateEmployeeAdminForm.getSalary() );
        }
        if ( updateEmployeeAdminForm.getLabelColor() != null ) {
            employee.setLabelColor( updateEmployeeAdminForm.getLabelColor() );
        }
        if ( updateEmployeeAdminForm.getPlaceOfIssue() != null ) {
            employee.setPlaceOfIssue( updateEmployeeAdminForm.getPlaceOfIssue() );
        }
        if ( updateEmployeeAdminForm.getIdentityNumber() != null ) {
            employee.setIdentityNumber( updateEmployeeAdminForm.getIdentityNumber() );
        }
        if ( updateEmployeeAdminForm.getBankNo() != null ) {
            employee.setBankNo( updateEmployeeAdminForm.getBankNo() );
        }
        if ( updateEmployeeAdminForm.getStatus() != null ) {
            employee.setStatus( updateEmployeeAdminForm.getStatus() );
        }
    }

    @Override
    public EmployeeDto fromEntityToEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto employeeDto = new EmployeeDto();

        employeeDto.setEmployeePhone( employeeAccountPhone( employee ) );
        employeeDto.setBirthday( employee.getBirthday() );
        employeeDto.setNote( employee.getNote() );
        employeeDto.setEmployeeFullName( employeeAccountFullName( employee ) );
        employeeDto.setSex( employee.getSex() );
        employeeDto.setEmployeeEmail( employeeAccountEmail( employee ) );
        employeeDto.setBranchName( employee.getBranchName() );
        employeeDto.setBankName( employee.getBankName() );
        employeeDto.setDateOfIssue( employee.getDateOfIssue() );
        employeeDto.setSalary( employee.getSalary() );
        employeeDto.setEmployeeAddress( employee.getAddress() );
        employeeDto.setLabelColor( employee.getLabelColor() );
        employeeDto.setPlaceOfIssue( employee.getPlaceOfIssue() );
        employeeDto.setCreatedDate( employee.getCreatedDate() );
        employeeDto.setCreatedBy( employee.getCreatedBy() );
        employeeDto.setBankNo( employee.getBankNo() );
        employeeDto.setModifiedDate( employee.getModifiedDate() );
        employeeDto.setEmployeeAvatarPath( employeeAccountAvatarPath( employee ) );
        employeeDto.setModifiedBy( employee.getModifiedBy() );
        employeeDto.setId( employee.getId() );
        employeeDto.setStatus( employee.getStatus() );

        return employeeDto;
    }

    @Override
    public List<EmployeeDto> fromEntityListToAdminDtoList(List<Employee> content) {
        if ( content == null ) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<EmployeeDto>( content.size() );
        for ( Employee employee : content ) {
            list.add( fromEntityToEmployeeDto( employee ) );
        }

        return list;
    }

    protected Account createEmployeeFormToAccount(CreateEmployeeForm createEmployeeForm) {
        if ( createEmployeeForm == null ) {
            return null;
        }

        Account account = new Account();

        account.setAvatarPath( createEmployeeForm.getEmployeeAvatarPath() );
        account.setEmail( createEmployeeForm.getEmployeeEmail() );
        account.setUsername( createEmployeeForm.getEmployeeUsername() );
        account.setFullName( createEmployeeForm.getEmployeeFullName() );
        account.setPhone( createEmployeeForm.getEmployeePhone() );

        return account;
    }

    protected void updateEmployeeAdminFormToAccount(UpdateEmployeeAdminForm updateEmployeeAdminForm, Account mappingTarget) {
        if ( updateEmployeeAdminForm == null ) {
            return;
        }

        if ( updateEmployeeAdminForm.getEmployeeAvatarPath() != null ) {
            mappingTarget.setAvatarPath( updateEmployeeAdminForm.getEmployeeAvatarPath() );
        }
        if ( updateEmployeeAdminForm.getEmployeeEmail() != null ) {
            mappingTarget.setEmail( updateEmployeeAdminForm.getEmployeeEmail() );
        }
        if ( updateEmployeeAdminForm.getEmployeeFullName() != null ) {
            mappingTarget.setFullName( updateEmployeeAdminForm.getEmployeeFullName() );
        }
    }

    private String employeeAccountPhone(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Account account = employee.getAccount();
        if ( account == null ) {
            return null;
        }
        String phone = account.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String employeeAccountFullName(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Account account = employee.getAccount();
        if ( account == null ) {
            return null;
        }
        String fullName = account.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String employeeAccountEmail(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Account account = employee.getAccount();
        if ( account == null ) {
            return null;
        }
        String email = account.getEmail();
        if ( email == null ) {
            return null;
        }
        return email;
    }

    private String employeeAccountAvatarPath(Employee employee) {
        if ( employee == null ) {
            return null;
        }
        Account account = employee.getAccount();
        if ( account == null ) {
            return null;
        }
        String avatarPath = account.getAvatarPath();
        if ( avatarPath == null ) {
            return null;
        }
        return avatarPath;
    }
}
