package com.landingis.api.dto.orders;

import com.landingis.api.dto.ABasicAdminDto;
import com.landingis.api.dto.collaborator.CollaboratorDto;
import com.landingis.api.dto.customer.CustomerDto;
import com.landingis.api.dto.employee.EmployeeDto;
import com.landingis.api.form.orders.CreateOrdersDetailForm;
import com.landingis.api.storage.model.OrdersDetail;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class OrdersDto extends ABasicAdminDto {
    private Long id;

    private Double totalMoney = 0d;
    private Integer saleOff = 0;
    private Integer vat = 0;

    //0 created, 1 accepted, 2 shipping, 3 done, 4 cancel
    private Integer state;
    private Integer prevState;

    private String code;            //6 random digits
    private Integer paymentMethod;  //De
    private String address;
    private String document;

    private String receiverName;
    private String receiverPhone;
    private CollaboratorDto collaboratorDto;
    private CustomerDto customerDto;
    private EmployeeDto employeeDto;

    private List<OrdersDetailDto> ordersDetailDtoList;

}
