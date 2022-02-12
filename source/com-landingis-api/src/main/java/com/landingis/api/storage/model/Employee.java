package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name = TablePrefix.PREFIX_TABLE + "employee")
public class Employee extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id")
    @MapsId
    private Account account;

    private Date birthday;
    private Integer sex;

    @Column(name = "agency_address")
    private String address;

    private String identityNumber;
    private Date dateOfIssue;
    private  String placeOfIssue;

    private String bankNo;
    private String bankName;
    private String branchName;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "label_color")
    private String labelColor;
    private Double salary = 0d;
    @Column(name = "is_admin_create")
    private Boolean isAdminCreated = false;

}
