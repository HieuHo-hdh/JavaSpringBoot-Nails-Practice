package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface OrdersTotalMoneyReportRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {
    @Query(value =
            "select * from nails_orders where state = :state and created_date BETWEEN :FromDate and :ToDate", nativeQuery = true)
    List<Orders> getOrdersTotalMoneyFromDateToDate(@Param("FromDate") Date FromDate, @Param("ToDate") Date ToDate, @Param("state") Integer state);

    @Query(value =
            "select * from nails_orders where created_date >= :FromDate and state = :state", nativeQuery = true)
    List<Orders> getOrdersTotalMoneyFromDate(@Param("FromDate") Date FromDate, @Param("state") Integer state);

    @Query(value =
            "select * from nails_orders where created_date <= :ToDate and state = :state", nativeQuery = true)
    List<Orders> getOrdersTotalMoneyToDate(@Param("ToDate") Date ToDate, @Param("state") Integer state);

    @Query(value =
            "select * from nails_orders where state = :state", nativeQuery = true)
    List<Orders> getOrdersTotalMoney(@Param("state") Integer state);

}
