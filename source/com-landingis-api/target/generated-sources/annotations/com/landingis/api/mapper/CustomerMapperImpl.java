package com.landingis.api.mapper;

import com.landingis.api.dto.customer.CustomerDto;
import com.landingis.api.form.customer.CreateCustomerForm;
import com.landingis.api.form.customer.UpdateCustomerAdminForm;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Customer;
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
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer fromCreateCustomerFormToEntity(CreateCustomerForm createCustomerForm) {
        if ( createCustomerForm == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setAccount( createCustomerFormToAccount( createCustomerForm ) );
        customer.setBirthday( createCustomerForm.getBirthday() );
        customer.setNote( createCustomerForm.getNote() );
        customer.setAddress( createCustomerForm.getCustomerAddress() );
        customer.setSex( createCustomerForm.getSex() );
        customer.setIsLoyalty( createCustomerForm.getIsLoyalty() );
        customer.setLoyaltyLevel( createCustomerForm.getLoyaltyLevel() );
        customer.setSaleOff( createCustomerForm.getSaleOff() );
        customer.setStatus( createCustomerForm.getStatus() );

        return customer;
    }

    @Override
    public void fromUpdateCustomerAdminFormToEntity(UpdateCustomerAdminForm updateCustomerAdminForm, Customer customer) {
        if ( updateCustomerAdminForm == null ) {
            return;
        }

        if ( customer.getAccount() == null ) {
            customer.setAccount( new Account() );
        }
        updateCustomerAdminFormToAccount( updateCustomerAdminForm, customer.getAccount() );
        if ( updateCustomerAdminForm.getBirthday() != null ) {
            customer.setBirthday( updateCustomerAdminForm.getBirthday() );
        }
        if ( updateCustomerAdminForm.getNote() != null ) {
            customer.setNote( updateCustomerAdminForm.getNote() );
        }
        if ( updateCustomerAdminForm.getCustomerAddress() != null ) {
            customer.setAddress( updateCustomerAdminForm.getCustomerAddress() );
        }
        if ( updateCustomerAdminForm.getSex() != null ) {
            customer.setSex( updateCustomerAdminForm.getSex() );
        }
        if ( updateCustomerAdminForm.getIsLoyalty() != null ) {
            customer.setIsLoyalty( updateCustomerAdminForm.getIsLoyalty() );
        }
        if ( updateCustomerAdminForm.getLoyaltyLevel() != null ) {
            customer.setLoyaltyLevel( updateCustomerAdminForm.getLoyaltyLevel() );
        }
        if ( updateCustomerAdminForm.getSaleOff() != null ) {
            customer.setSaleOff( updateCustomerAdminForm.getSaleOff() );
        }
        if ( updateCustomerAdminForm.getStatus() != null ) {
            customer.setStatus( updateCustomerAdminForm.getStatus() );
        }
    }

    @Override
    public CustomerDto fromEntityToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setCustomerFullName( customerAccountFullName( customer ) );
        customerDto.setCustomerAddress( customer.getAddress() );
        customerDto.setBirthday( customer.getBirthday() );
        customerDto.setNote( customer.getNote() );
        customerDto.setSex( customer.getSex() );
        customerDto.setIsLoyalty( customer.getIsLoyalty() );
        customerDto.setCustomerPhone( customerAccountPhone( customer ) );
        customerDto.setCreatedDate( customer.getCreatedDate() );
        customerDto.setLoyaltyLevel( customer.getLoyaltyLevel() );
        customerDto.setCustomerAvatarPath( customerAccountAvatarPath( customer ) );
        customerDto.setCreatedBy( customer.getCreatedBy() );
        customerDto.setCustomerEmail( customerAccountEmail( customer ) );
        customerDto.setModifiedDate( customer.getModifiedDate() );
        customerDto.setModifiedBy( customer.getModifiedBy() );
        customerDto.setId( customer.getId() );
        customerDto.setSaleOff( customer.getSaleOff() );
        customerDto.setStatus( customer.getStatus() );

        return customerDto;
    }

    @Override
    public List<CustomerDto> fromEntityListToAdminDtoList(List<Customer> content) {
        if ( content == null ) {
            return null;
        }

        List<CustomerDto> list = new ArrayList<CustomerDto>( content.size() );
        for ( Customer customer : content ) {
            list.add( fromEntityToCustomerDto( customer ) );
        }

        return list;
    }

    protected Account createCustomerFormToAccount(CreateCustomerForm createCustomerForm) {
        if ( createCustomerForm == null ) {
            return null;
        }

        Account account = new Account();

        account.setAvatarPath( createCustomerForm.getCustomerAvatarPath() );
        account.setEmail( createCustomerForm.getCustomerEmail() );
        account.setFullName( createCustomerForm.getCustomerFullName() );
        account.setPhone( createCustomerForm.getCustomerPhone() );

        return account;
    }

    protected void updateCustomerAdminFormToAccount(UpdateCustomerAdminForm updateCustomerAdminForm, Account mappingTarget) {
        if ( updateCustomerAdminForm == null ) {
            return;
        }

        if ( updateCustomerAdminForm.getCustomerAvatarPath() != null ) {
            mappingTarget.setAvatarPath( updateCustomerAdminForm.getCustomerAvatarPath() );
        }
        if ( updateCustomerAdminForm.getCustomerEmail() != null ) {
            mappingTarget.setEmail( updateCustomerAdminForm.getCustomerEmail() );
        }
        if ( updateCustomerAdminForm.getCustomerFullName() != null ) {
            mappingTarget.setFullName( updateCustomerAdminForm.getCustomerFullName() );
        }
    }

    private String customerAccountFullName(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Account account = customer.getAccount();
        if ( account == null ) {
            return null;
        }
        String fullName = account.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }

    private String customerAccountPhone(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Account account = customer.getAccount();
        if ( account == null ) {
            return null;
        }
        String phone = account.getPhone();
        if ( phone == null ) {
            return null;
        }
        return phone;
    }

    private String customerAccountAvatarPath(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Account account = customer.getAccount();
        if ( account == null ) {
            return null;
        }
        String avatarPath = account.getAvatarPath();
        if ( avatarPath == null ) {
            return null;
        }
        return avatarPath;
    }

    private String customerAccountEmail(Customer customer) {
        if ( customer == null ) {
            return null;
        }
        Account account = customer.getAccount();
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
