package com.landingis.api.mapper;

import com.landingis.api.dto.orders.OrdersDto;
import com.landingis.api.form.orders.CreateOrdersForm;
import com.landingis.api.form.orders.UpdateOrdersForm;
import com.landingis.api.form.orders.UpdateOrdersStateForm;
import com.landingis.api.storage.model.Orders;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-03T13:42:46+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
@Component
public class OrdersMapperImpl implements OrdersMapper {

    @Override
    public Orders fromCreateOrdersFormToEntity(CreateOrdersForm createOrdersForm) {
        if ( createOrdersForm == null ) {
            return null;
        }

        Orders orders = new Orders();

        orders.setAddress( createOrdersForm.getOrdersAddress() );
        orders.setDocument( createOrdersForm.getOrdersDocument() );
        orders.setSaleOff( createOrdersForm.getOrdersSaleOff() );

        return orders;
    }

    @Override
    public Orders fromUpdateOrdersFormToEntity(UpdateOrdersForm updateOrdersForm, Orders orders) {
        if ( updateOrdersForm == null ) {
            return null;
        }

        if ( updateOrdersForm.getOrdersAddress() != null ) {
            orders.setAddress( updateOrdersForm.getOrdersAddress() );
        }
        if ( updateOrdersForm.getId() != null ) {
            orders.setId( updateOrdersForm.getId() );
        }
        if ( updateOrdersForm.getOrdersSaleOff() != null ) {
            orders.setSaleOff( updateOrdersForm.getOrdersSaleOff() );
        }

        return orders;
    }

    @Override
    public void fromUpdateOrdersStateFormToEntity(UpdateOrdersStateForm updateOrdersStateForm, Orders orders) {
        if ( updateOrdersStateForm == null ) {
            return;
        }

        if ( updateOrdersStateForm.getId() != null ) {
            orders.setId( updateOrdersStateForm.getId() );
        }
        if ( updateOrdersStateForm.getOrdersState() != null ) {
            orders.setState( updateOrdersStateForm.getOrdersState() );
        }
    }

    @Override
    public OrdersDto fromEntityToOrdersDto(Orders address) {
        if ( address == null ) {
            return null;
        }

        OrdersDto ordersDto = new OrdersDto();

        ordersDto.setCode( address.getCode() );
        ordersDto.setAddress( address.getAddress() );
        ordersDto.setDocument( address.getDocument() );
        ordersDto.setTotalMoney( address.getTotalMoney() );
        ordersDto.setVat( address.getVat() );
        ordersDto.setPrevState( address.getPrevState() );
        ordersDto.setCreatedDate( address.getCreatedDate() );
        ordersDto.setCreatedBy( address.getCreatedBy() );
        ordersDto.setModifiedDate( address.getModifiedDate() );
        ordersDto.setPaymentMethod( address.getPaymentMethod() );
        ordersDto.setModifiedBy( address.getModifiedBy() );
        ordersDto.setId( address.getId() );
        ordersDto.setSaleOff( address.getSaleOff() );
        ordersDto.setState( address.getState() );
        ordersDto.setStatus( address.getStatus() );

        return ordersDto;
    }

    @Override
    public List<OrdersDto> fromEntityListToOrdersDtoList(List<Orders> content) {
        if ( content == null ) {
            return null;
        }

        List<OrdersDto> list = new ArrayList<OrdersDto>( content.size() );
        for ( Orders orders : content ) {
            list.add( fromEntityToOrdersDto( orders ) );
        }

        return list;
    }
}
