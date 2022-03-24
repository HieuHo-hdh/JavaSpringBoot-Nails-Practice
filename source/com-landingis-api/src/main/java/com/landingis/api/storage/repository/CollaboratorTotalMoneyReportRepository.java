package com.landingis.api.storage.repository;

import com.landingis.api.storage.model.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CollaboratorTotalMoneyReportRepository extends JpaRepository<Collaborator, Long>, JpaSpecificationExecutor<Collaborator> {
    @Query(value =
            "select sum(total_money) from nails_orders where state = :state and orders_collaborator_id = :ordersCollaboratorId and created_date BETWEEN :FromDate and :ToDate", nativeQuery = true)
    Double getOrdersTotalMoneyFromDateToDate(@Param("FromDate") Date FromDate, @Param("ToDate") Date ToDate, @Param("state") Integer state, @Param("ordersCollaboratorId") Long ordersCollaboratorId);

    @Query(value =
            "select sum(total_money) from nails_orders where created_date >= :FromDate and state = :state and orders_collaborator_id = :ordersCollaboratorId", nativeQuery = true)
    Double getOrdersTotalMoneyFromDate(@Param("FromDate") Date FromDate, @Param("state") Integer state, @Param("ordersCollaboratorId") Long ordersCollaboratorId);

    @Query(value =
            "select sum(total_money) from nails_orders where created_date <= :ToDate and state = :state and orders_collaborator_id = :ordersCollaboratorId", nativeQuery = true)
    Double getOrdersTotalMoneyToDate(@Param("ToDate") Date ToDate, @Param("state") Integer state, @Param("ordersCollaboratorId") Long ordersCollaboratorId);

    @Query(value =
            "select sum(total_money) from nails_orders where state = :state and orders_collaborator_id = :ordersCollaboratorId", nativeQuery = true)
    Double getOrdersTotalMoney(@Param("state") Integer state, @Param("ordersCollaboratorId") Long ordersCollaboratorId);

}
