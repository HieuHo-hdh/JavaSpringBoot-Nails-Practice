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

    @Column(name="identity_number")
    private String identityNumber;

    @Column(name="date_of_issue")
    private Date dateOfIssue;

    @Column(name="place_of_issue")
    private  String placeOfIssue;

    @Column(name="bank_no")
    private String bankNo;

    @Column(name="bank_name")
    private String bankName;

    @Column(name="branch_name")
    private String branchName;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "label_color")
    private String labelColor;
    private Double salary = 0d;
    @Column(name = "is_admin_create")
    private Boolean isAdminCreated = false;

}
