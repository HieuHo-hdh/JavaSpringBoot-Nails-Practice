package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = TablePrefix.PREFIX_TABLE + "customer")
public class Customer extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")    //Specifies a column for joining an entity association
    @MapsId
    private Account account;    //1 customer has 1 account only

    private String address;

    private Date birthday;
    private Integer sex;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "loyalty_date")
    private Date loyaltyDate;

    @Column(name = "is_loyalty")
    private Boolean isLoyalty = false;

    @Column(name = "loyalty_level")
    private Integer loyaltyLevel;

    @Column(name = "sale_off")
    private Integer saleOff = 0;

    @Column(name = "is_admin_create")
    private Boolean isAdminCreated = false;
}
