package com.landingis.api.storage.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Data
@Table (name = TablePrefix.PREFIX_TABLE + "orders")
public class Orders extends  Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double totalMoney = 0d;
    private Integer saleOff = 0;
    private Integer vat = 0;

    //0 created, 1 accepted, 2 shipping, 3 done, 4 cancel
    private Integer state;
    @Column(name = "prev_state")
    private Integer prevState;

    private String code;            //6 random digits
    private Integer paymentMethod;  //De
    private String address;

    private String document;

    private String receiverName;
    private String receiverPhone;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "collaborator_id")
    @JoinColumn(name = "orders_collaborator_id")
    private Collaborator collaborator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

//    private OrdersDetail ordersDetail;
}
