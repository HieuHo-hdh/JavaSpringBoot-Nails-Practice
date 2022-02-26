package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = TablePrefix.PREFIX_TABLE + "orders_detail")
public class OrdersDetail extends  Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer kind;
    private Integer amount;
    private Double price;
    private Integer value;
    private Integer collaboratorCommission;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
}
