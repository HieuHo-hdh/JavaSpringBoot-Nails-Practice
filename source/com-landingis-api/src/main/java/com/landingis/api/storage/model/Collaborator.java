package com.landingis.api.storage.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table (name=TablePrefix.PREFIX_TABLE + "collaborator")
public class Collaborator extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    private Date birthday;
    private Integer sex;

    @Column(name="agency_address")
    private String address;

    private String identityNumber;
    private Date dateOfIssue;
    private String placeOfIssue;

    private String bankNo;
    private String bankName;
    private String branchName;

    @Column(name="note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "is_admin_create")
    private Boolean isAdminCreated = false;
}
