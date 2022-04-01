package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name=TablePrefix.PREFIX_TABLE + "salary")
public class Salary extends Auditable<String>{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salary_period_id")
    private SalaryPeriod salaryPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account user;

    @Column(name="user_kind")
    private Integer userKind;

    @Column(name="is_payment")
    private Boolean isPayment = false;

    private Double commission = 0d;
    private Double revenue = 0d;
    @Column(name="salary_price")
    private Double salaryPrice = 0d;
    @Column(name="pay_date")
    private Date payDate;
}
