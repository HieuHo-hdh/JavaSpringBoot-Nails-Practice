package com.landingis.api.storage.model;

import com.landingis.api.constant.LandingISConstant;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name=TablePrefix.PREFIX_TABLE + "salary_period")
public class SalaryPeriod extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //Disable updating startDate
    @Column(name="start_date", nullable = false, updatable = false)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    private Integer state = LandingISConstant.SALARY_PERIOD_STATE_PENDING;

    private Double totalRevenue = 0d;
    private Double totalSalary = 0d;

}
