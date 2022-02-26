package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdersDetailRepository extends JpaRepository<OrdersDetail, Long>, JpaSpecificationExecutor<OrdersDetail> {

}