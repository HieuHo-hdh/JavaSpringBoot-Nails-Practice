package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = TablePrefix.PREFIX_TABLE+"addresses")
public class Address extends Auditable<String>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private String name;
    private String phone;
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private Province district;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commune_id")
    private Province commune;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id")
    private Province province;
}