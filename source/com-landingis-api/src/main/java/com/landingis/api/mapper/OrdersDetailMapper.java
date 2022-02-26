package com.landingis.api.mapper;

import com.landingis.api.dto.orders.OrdersDto;
import com.landingis.api.form.orders.CreateOrdersDetailForm;
import com.landingis.api.form.orders.CreateOrdersForm;
import com.landingis.api.storage.model.Orders;
import com.landingis.api.storage.model.OrdersDetail;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrdersDetailMapper {

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "note", target = "note")
    @Mapping(source = "amount", target = "amount")
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersCreateMapping")
    OrdersDetail fromCreateOrdersFormToEntity(CreateOrdersDetailForm createOrdersForm);

    @IterableMapping(elementTargetType = OrdersDetail.class, qualifiedByName = "adminCreateMapping")
    List<OrdersDetail> fromCreateOrdersFormToEntityList(List<CreateOrdersDetailForm> createOrdersDetailFormList);

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
    @BeanMapping(ignoreByDefault = true)
    @Named("ordersMapping")
    OrdersDto fromEntityToOrdersDto(Orders address);

    @IterableMapping(elementTargetType = OrdersDto.class, qualifiedByName = "ordersMapping")
    List<OrdersDto> fromEntityListToOrdersDtoList(List<Orders> content);
}
