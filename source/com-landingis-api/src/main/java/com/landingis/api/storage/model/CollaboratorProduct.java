package com.landingis.api.storage.model;

import com.landingis.api.storage.model.Auditable;
import com.landingis.api.storage.model.Collaborator;
import com.landingis.api.storage.model.Product;
import com.landingis.api.storage.model.TablePrefix;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = TablePrefix.PREFIX_TABLE+"collaborator_product",
        uniqueConstraints = { @UniqueConstraint(columnNames = { "collaborator_id", "product_id" }) })
public class CollaboratorProduct extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator_id")
    private Collaborator collaborator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer kind;
    private Double value;
}
