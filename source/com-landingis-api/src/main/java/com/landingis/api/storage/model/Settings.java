package com.landingis.api.storage.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = TablePrefix.PREFIX_TABLE + "settings")
public class Settings extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;   //open time

    @Column(name = "settings_key")
    private String key;    //openTime

    @Column(name = "settings_value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "settings_group")
    private String group;
    //General:  0::general
    //Admin:    1::admin
    //Customer: 2::store

    private Integer groupId;    //Setting belongs to customer site or admin site

    @Column(name = "editable")
    private boolean editable;

    private Integer kind;   //Determine value kind


}
