package com.landingis.api.mapper;

import com.landingis.api.dto.orders.OrdersDto;
import com.landingis.api.dto.orders.OrdersTotalMoneyReportDto;
import com.landingis.api.form.orders.CreateOrdersForm;
import com.landingis.api.form.orders.UpdateOrdersForm;
import com.landingis.api.form.orders.UpdateOrdersStateForm;
import com.landingis.api.storage.model.Orders;
import com.landingis.api.storage.model.Orders;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdersMapper {

//    @Mapping(source = "customerEmail", target = "customer.email")
//    @Mapping(source = "customerFullName", target = "customer.fullName")
//    @Mapping(source = "customerPhone", target = "customer.phone")
    @Mapping(source = "collaboratorId", target = "collaborator.id")
    @Mapping(source = "ordersAddress", target = "address")
    @Mapping(source = "ordersDocument", target = "document")
    @Mapping(source = "ordersSaleOff", target = "saleOff")
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersCreateMapping")
    Orders fromCreateOrdersFormToEntity(CreateOrdersForm createOrdersForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "ordersAddress", target = "address")
    @Mapping(source = "ordersSaleOff", target = "saleOff")
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersUpdateMapping")
    Orders fromUpdateOrdersFormToEntity(UpdateOrdersForm updateOrdersForm, @MappingTarget Orders orders);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "ordersState", target = "state")
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersUpdateStateMapping")
    void fromUpdateOrdersStateFormToEntity(UpdateOrdersStateForm updateOrdersStateForm, @MappingTarget Orders orders);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "totalMoney", target = "totalMoney")
    @Mapping(source = "saleOff", target = "saleOff")
    @Mapping(source = "vat", target = "vat")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "prevState", target = "prevState")
    @Mapping(source = "code", target = "code")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "document", target = "document")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "collaborator", target = "collaboratorDto")

    @BeanMapping(ignoreByDefault = true)
    @Named("ordersMapping")
    OrdersDto fromEntityToOrdersDto(Orders address);

    @IterableMapping(elementTargetType = OrdersDto.class, qualifiedByName = "ordersMapping")
    List<OrdersDto> fromEntityListToOrdersDtoList(List<Orders> content);

//    @Mapping(source = "id", target = "id")
    @Mapping(source = "orders.id", target = "ordersId")
    @Mapping(source = "collaborator.account.fullName", target = "collaboratorFullName")
    @Mapping(source = "totalMoney", target = "totalMoney")
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersTotalMoneyReportMapping")
    OrdersTotalMoneyReportDto fromEntityToOrdersTotalMoneyReportDto(Orders orders);

    @IterableMapping(elementTargetType = OrdersTotalMoneyReportDto.class, qualifiedByName = "ordersTotalMoneyReportMapping")
    List<OrdersTotalMoneyReportDto> fromEntityListToOrdersTotalMoneyReportDtoList(List<Orders> content);
}
