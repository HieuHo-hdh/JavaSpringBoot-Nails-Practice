package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long>, JpaSpecificationExecutor<OrdersDetail> {
//    public OrdersDetail findByOrderId(Long ordersId);
    //Find List because there are many orders details
    public List<OrdersDetail> findByOrdersId(Long ordersId);

}